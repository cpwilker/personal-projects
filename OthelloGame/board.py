from tiles import Tiles
from game_controller import GameController


class Board():
    """Displays the board and interacts with tiles object"""

    white = 255
    black = 0
    player_turn = True

    def __init__(self, R, G, B, HEIGHT, WIDTH, STROKE, WEIGHT, SIZE, N, gc):
        """Initializes board with specific dimensions and color
        Int, Int, Int, Int, Int, Int, Int, Int, Int, GameController object -->
        None
        """
        self.R = R
        self.G = G
        self.B = B
        self.HEIGHT = HEIGHT
        self.WIDTH = WIDTH
        self.STROKE = STROKE
        self.WEIGHT = WEIGHT
        self.SIZE = SIZE
        self.N = N
        self.tile = Tiles(self.N, (self.R, self.G, self.B))
        self.disks = self.tile.all_tiles
        self.gc = gc
        self.full = self.N**2
        self.whites = 0
        self.blacks = 0
        self.total = 0
        self.end_game = False
        self.done = False

    def count_colors(self):
        ''' Adds up the current number of white and back tiles.
        None --> None
        '''
        self.whites = 0
        self.blacks = 0
        for i in range(self.N):
            for j in range(self.N):
                if self.disks[i][j].fill_color == 'white':
                    self.whites += 1
                elif self.disks[i][j].fill_color == 'black':
                    self.blacks += 1

    def update(self):
        '''Check if board is full and count winner
        None -> None'''
        if not (self.gc.white_wins or self.gc.black_wins
                or self.gc.tie):
            self.total = self.whites + self.blacks
            # end_game accounts for when the board is not full
            # but there are no more legal moves so game over
            if self.total == self.full or self.end_game:
                if self.whites > self.blacks:
                    self.gc.winning_num = self.whites
                    self.gc.white_wins = True
                elif self.blacks > self.whites:
                    self.gc.winning_num = self.blacks
                    self.gc.black_wins = True
                else:
                    self.gc.winning_num = self.blacks
                    self.gc.tie = True
                self.done = True

    def display(self):
        """Displays board, calls tiles methods to initiate game play and
        calls board methods to check for end of game. Keeps displaying board
        throughout game.
        None --> None
        """
        stroke(self.STROKE)
        strokeWeight(self.WEIGHT)
        background(self.R, self.G, self.B)

        for x in range(self.SIZE, self.WIDTH, self.SIZE):
            line(x, 0, x, self.HEIGHT)
        for y in range(self.SIZE, self.HEIGHT, self.SIZE):
            line(0, y, self.WIDTH, y)

        if self.disks == []:
            self.tile.get_all_tiles()
            self.tile.starting_four_tiles()
        self.count_colors()
        self.update()

        for i in range(self.N):
            for j in range(self.N):
                self.disks[i][j].display()
