
class Tile():
    '''This class initiates one tile object.'''

    white = 255
    black = 0
    green = (0, 100, 0)

    def __init__(self, column, row, color):
        ''' Initializes a tile object with given color and placement
        Int, Int, Int or tuple --> None
        '''
        self.color = color
        self.column = column
        self.row = row

    def display(self):
        '''Displays a tile to the screen when called. Accounts
        for a tuple color or just an int.
        None --> None'''
        if type(self.color) == tuple:
            (R, G, B) = self.color
            fill(R, G, B)
            stroke(R, G, B)
        else:
            fill(self.color)
            stroke(self.color)
        ellipse(self.column * 100 + 50, self.row * 100 + 50, 90, 90)

    @property
    def fill_color(self):
        '''Property that returns a string indicating the color of the tile
        None --> Str
        '''
        if self.color == self.white:
            return 'white'
        elif self.color == self.black:
            return 'black'
        else:
            return 'green'
