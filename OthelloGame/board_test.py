from board import Board
from game_controller import GameController


def test_constructor():
    '''Test board initializes correctly and all starting values are as
    expected'''
    gc = GameController(400, 400)
    bd = Board(0, 100, 0, 400, 400, 0, 4, 100, 4, gc)
    assert bd.R == 0
    assert bd.G == 100
    assert bd.B == 0
    assert bd.HEIGHT == 400
    assert bd.WIDTH == 400
    assert bd.STROKE == 0
    assert bd.WEIGHT == 4
    assert bd.SIZE == 100
    assert bd.N == 4
    assert bd.gc == gc
    assert bd.whites == 0
    assert bd.blacks == 0
    assert bd.disks == []
    assert bd.full == 16
    assert bd.total == 0
    assert not bd.end_game
    assert not bd.done


def test_count_colors():
    '''Test count_colors method to make sure the correct number
    of tiles of each color are being counted.
    '''
    gc = GameController(400, 400)
    bd = Board(0, 100, 0, 400, 400, 0, 4, 100, 4, gc)
    bd.tile.get_all_tiles()
    bd.tile.starting_four_tiles()
    bd.count_colors()
    assert bd.whites == 2
    assert bd.blacks == 2
    bd.tile.change_color(0, 0, 0)
    bd.count_colors()
    assert bd.blacks == 3


def test_update():
    '''Test update method to make sure a winner is chosen when the board
    is full or end_game is true.
    '''
    gc = GameController(400, 400)
    bd = Board(0, 100, 0, 400, 400, 0, 4, 100, 4, gc)
    bd.tile.get_all_tiles()
    bd.tile.starting_four_tiles()
    bd.count_colors()
    bd.update()
    assert not bd.full == bd.total
    bd.whites = 8
    bd.blacks = 8
    bd.update()
    assert bd.total == bd.full
    assert bd.gc.tie
    gc = GameController(400, 400)
    bd = Board(0, 100, 0, 400, 400, 0, 4, 100, 4, gc)
    bd.tile.get_all_tiles()
    bd.end_game = True
    bd.whites = 7
    bd.blacks = 9
    bd.update()
    assert bd.gc.black_wins
