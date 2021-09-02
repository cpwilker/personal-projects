from tile import Tile


class Tiles():
    """"
    This class initiates a list of lists containing tile objects. This
    represents all tiles on the board, initially none, and interacts
    with the tile's colors. This class has access to the Tile class.
    All tiles on board start green.
    """

    WHITE = 255
    BLACK = 0

    def __init__(self, n, color):
        """Initializes list of tiles representing all tiles on
        the nxn board. Tiles start out the color of the board, passed
        as an argument.
        Int, Tuple --> None
        """
        self.color = color
        self.n = n
        self.all_tiles = []  # list of lists

    def get_all_tiles(self):
        '''
        Puts a green tile in each column, row space on the board.
        Does this by having a list of lists, each list representing a column,
        each index in each list representing a row.
        None --> None
        '''
        # n columns and n rows
        for i in range(self.n):
            column_tiles = []
            for j in range(self.n):
                tile = Tile(i, j, self.color)
                column_tiles.append(tile)
            self.all_tiles.append(column_tiles)

    def change_color(self, column, row, color):
        '''Replaces tile object at specified location with new
        tile object with param color as new color.
        Int, Int, Int or Tuple --> None
        '''
        new_tile = Tile(column, row, color)
        self.all_tiles[column][row] = new_tile

    def starting_four_tiles(self):
        ''' Changes color of the middle 4 tiles to alternate
        white and black.
        None --> None
        '''
        mid_point = int(self.n/2)
        for i in range(mid_point-1, mid_point+1):
            self.change_color(i, i, self.WHITE)
        self.change_color(mid_point-1, mid_point, self.BLACK)
        self.change_color(mid_point, mid_point-1, self.BLACK)
