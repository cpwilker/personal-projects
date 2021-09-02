from tiles import Tiles


def test_constructor():
    ''' Test that color and list size is set up correctly and that
    initial tile list is empty
    '''
    tiles = Tiles(4, 255)
    assert tiles.color == 255
    assert tiles.n == 4
    assert tiles.all_tiles == []


def test_get_all_tiles():
    '''Test get_all_tiles by making sure all_tiles populates with n
    lists and that these lists contain tile objets that are set up
    as expected.'''
    tiles = Tiles(4, 255)
    tiles.get_all_tiles()
    assert len(tiles.all_tiles) == 4
    assert type(tiles.all_tiles[0]) == list
    assert tiles.all_tiles[1][2].column == 1
    assert tiles.all_tiles[1][2].row == 2


def test_change_color():
    '''Test change_color by seeing if new tile object with different
    color is put in correct place when change_color is called.
    '''
    tiles = Tiles(4, 255)
    tiles.get_all_tiles()
    tiles.change_color(1, 2, 0)
    assert tiles.all_tiles[1][2].fill_color == 'black'
    tiles = Tiles(4, 0)
    tiles.get_all_tiles()
    tiles.change_color(0, 3, (0, 100, 0))
    assert tiles.all_tiles[0][3].fill_color == 'green'
    assert tiles.all_tiles[1][3].fill_color == 'black'


def test_starting_four_tiles():
    '''Test starting_four_tiles to make sure the correct tiles
    are changed to their correct color when called.
    '''
    tiles = Tiles(4, (0, 100, 0))
    tiles.get_all_tiles()
    tiles.starting_four_tiles()
    assert tiles.all_tiles[1][1].fill_color == 'white'
    assert tiles.all_tiles[2][2].fill_color == 'white'
    assert tiles.all_tiles[1][2].fill_color == 'black'
    assert tiles.all_tiles[2][1].fill_color == 'black'
    assert tiles.all_tiles[0][0].fill_color == 'green'
