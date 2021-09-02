

class GameManager():
    """This class acts as the AI that decides moves to make based on
    highest possible yeild of white tiles each turn."""

    white = 255
    black = 0

    def __init__(self, board):
        self.player_turn = True
        self.board = board
        self.print_turn = True
        self.prompt_name = True

    def is_legal_move(self, orig_x, orig_y):
        """Look at tile at orig_x, orig_y and return False is move is not
        legal. If move is legal, return a list of spaces that would turn
        player/ai's color
        For player turn, needs to take in adjustment for mouseX, mouseY /SIZE
        """
        to_be_flipped = []
        # check is space is green and on board
        if ((self.board.disks[orig_x][orig_y].fill_color != 'green') or
                not self.real_space(orig_x, orig_y)):
            return False

        if self.player_turn:
            my_color = 'black'
            other_color = 'white'
        else:
            my_color = 'white'
            other_color = 'black'

        # Going to have to check rows, columns, and verticle in both
        # directions:
        direction_list = [[0, 1], [1, 0], [1, 1], [-1, 1], [-1, 0], [-1, -1],
                          [0, -1], [1, -1]]
        # Check x, y in each direction aka each surrounding space
        for add_x, add_y in direction_list:
            x, y = orig_x, orig_y
            x += add_x
            y += add_y  # Now we are looking at a new space
            if (self.real_space(x, y) and (self.board.disks[x][y].fill_color ==
                                           other_color)):
                # Other player is in this space. Look at next tile in line
                x += add_x
                y += add_y
                if not self.real_space(x, y):
                    continue  # If next one over is off board, not a legal move
                # Check spaces in line until we find a diff color or go off
                # board
                while self.board.disks[x][y].fill_color == other_color:
                    x += add_x
                    y += add_y
                    if not self.real_space(x, y):  # Gone off board
                        break
                # If we reach a tile of our own color, then orig x,y is a
                # legal move as long as there are white tiles to flip
                if self.real_space(x, y):
                    if self.board.disks[x][y].fill_color == my_color:
                        # We have pieces to flip! Let's count them the
                        # opposite way until we reach the orig piece
                        # and add them to our flip list
                        while True:
                            x -= add_x
                            y -= add_y
                            if x == orig_x and y == orig_y:
                                break
                            to_be_flipped.append([x, y])

        # Check we have at least 1 tile to be flipped aka legal move
        if len(to_be_flipped) == 0:
            return False
        return to_be_flipped

    def ai_move_decision(self):
        """Returns the col, row coords for the best legal moves on the
        board for ai"""
        # Creates dictionary with column, row values of tiles that are legal
        # moves as keys and their # of tiles to be flipped to their color as
        # values
        moves_dict = {}
        for i in range(len(self.board.disks)):
            for j in range(len(self.board.disks)):
                if self.is_legal_move(i, j):
                    flip = len(self.is_legal_move(i, j))
                    moves_dict[(i, j)] = flip
        
        if moves_dict:
            # Sort this dictionary by values to find best play
            sorted_list = sorted(moves_dict.items(), key=lambda x: x[1],
                                 reverse=True)

            # This is our x, y tuple for what tile we should flip as the ai
            best_tile_tuple = sorted_list[0][0]
            return best_tile_tuple
        else:
            return False

    def real_space(self, x, y):
        """Returns true if x and y are col, row coords on the board"""
        if (x >= 0 and x <= (self.board.N - 1) and y >= 0 and y <=
                            (self.board.N - 1)):
            return True
        else:
            return False

    def flip_tiles(self, tile_list, color):
        """ Takes in a list of tile [x,y] coords that need their color
        changed to the opposite color. Calls the change_color tiles method
        to change each one based on whose turn it is.
        """
        for tile in tile_list:
            x = tile[0]
            y = tile[1]
            self.board.tile.change_color(x, y, color)

    def player_make_move(self, mouseX, mouseY):
        """Checks if square clicked is "empty" aka has a green
        tile. If so, changes the color to black for the player_turn and changes
        color to white if AI turn.
        Int, Int --> None
        """
        # Make sure there are legal moves left
        if self.ai_move_decision():
            r = mouseY//self.board.SIZE
            col = mouseX//self.board.SIZE
            if self.is_legal_move(col, r):
                if self.player_turn:
                    self.flip_tiles(self.is_legal_move(col, r), self.black)
                    self.board.tile.change_color(col, r, self.black)
                    self.player_turn = False
                    self.print_turn = True
        else:
            self.player_turn = False
            self.print_turn = True
            if not self.ai_move_decision():
                self.board.end_game = True

    def ai_make_move(self):
        """Calls the AI to make their move
        """
        tup = self.ai_move_decision()
        if tup:
            col = tup[0]
            r = tup[1]
            self.flip_tiles(self.is_legal_move(col, r), self.white)
            self.board.tile.change_color(col, r, self.white)
            self.player_turn = True
            self.print_turn = True
            if not self.ai_move_decision():
                self.player_turn = False
                self.print_turn = True
        else:
            self.player_turn = True
            self.print_turn = True
            if not self.ai_move_decision():
                self.board.end_game = True

    def update_turn(self):
        '''Shows turn to terminal only once and prompts user
        for name when game is finished
        None --> None
        '''
        if not self.board.done:
            if self.print_turn:
                if self.player_turn:
                    print("Player's move")
                    self.print_turn = False
                else:
                    print("Computer's move")
                    self.print_turn = False
        if self.board.done and self.prompt_name:
            name = self.input('Please enter your name: ')
            score = self.board.blacks
            self.prompt_name = False
            self.highest_score(name, score, 'scores.txt')

    def highest_score(self, name, score, file_name):
        '''This method reads in lines of file_name file and
        compares score parameter with highest score in file
        and puts new score first in file if highest or just
        appends to scores file.
        Str, Str, Str --> None
        '''
        f = open(file_name, 'r')
        lines = f.readlines()
        player_string = name + ' ' + str(score) + '\n'
        if lines:
            # highest score is first on file
            top_score_line = lines[0]
            top_score_line = top_score_line[: -1]
            top_score_list = top_score_line.rsplit(' ', 1)
            top_score = int(top_score_list[1])
            if score > top_score:
                lines.insert(0, player_string)
            else:
                lines.append(player_string)
        else:
            lines.append(player_string)
        f.close()
        f_write = open(file_name, 'w')
        for player in lines:
            f_write.write(player)
        f_write.close()

    def input(self, message=''):
        '''Input method for processing
        Str --> Processing input box
        '''
        from javax.swing import JOptionPane
        return JOptionPane.showInputDialog(frame, message)
