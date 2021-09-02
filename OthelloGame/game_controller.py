
class GameController():
    """This class displays the winner of the game and their number of tiles to
    the screen as well as prints it to the console when a winner is declared"""

    def __init__(self, WIDTH, HEIGHT):
        """Initializes GameController object
        Int, Int --> None
        """
        self.WIDTH = WIDTH
        self.HEIGHT = HEIGHT
        self.white_wins = False
        self.black_wins = False
        self.tie = False
        self.winning_num = 0
        self.end_game = False
        self.print_turn = True

    def update(self):
        ''' Displays text declaring winner of game to screen as well as
        number of winning tiles.
        None --> None
        '''
        if self.white_wins:
            fill(106, 20, 173)
            textSize(50)
            text("WHITE WINS WITH " + str(self.winning_num) + " TILES",
                 self.WIDTH/2 - 400, self.HEIGHT/2)
        if self.black_wins:
            fill(106, 20, 173)
            textSize(50)
            text("BLACK WINS WITH " + str(self.winning_num) + " TILES",
                 self.WIDTH/2 - 400, self.HEIGHT/2)
        if self.tie:
            fill(106, 20, 173)
            textSize(50)
            text("TIE WITH " + str(self.winning_num) + " TILES",
                 self.WIDTH/2 - 400, self.HEIGHT/2)

    def update_terminal(self):
        """ Prints winner of game and winning number of tiles to
        the terminal only once game is over.
        None --> prints string
        """
        if not self.end_game and (self.white_wins or self.black_wins or
                                  self.tie):
            if self.white_wins:
                print("WHITE WINS WITH " + str(self.winning_num) + " TILES")
            if self.black_wins:
                print("BLACK WINS WITH " + str(self.winning_num) + " TILES")
            if self.tie:
                print("TIE WITH " + str(self.winning_num) + " TILES")
            self.end_game = True

