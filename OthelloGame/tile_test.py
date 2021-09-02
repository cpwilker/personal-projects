from tile import Tile


def test_constructor():
    '''Test that tile object is created as expected
    '''
    tile = Tile(1, 2, 0)
    assert tile.column == 1
    assert tile.row == 2
    assert tile.color == 0


def fill_color_test():
    '''
    Test that fill_color returns the correct string with different
    inputs
    '''
    dot = Tile(1, 2, 0)
    assert dot.fill_color == 'black'
    dot = Tile(1, 2, 255)
    assert dot.fill_color == 'white'
    dot = Tile(1, 2, (0, 100, 0))
    assert dot.fill_color == 'green'
