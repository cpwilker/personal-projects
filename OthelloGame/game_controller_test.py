from game_controller import GameController


def test_constructor():
    '''Test initial set of game controller'''
    gc = GameController(800, 800)
    assert gc.WIDTH == 800
    assert gc.HEIGHT == 800
    assert not gc.white_wins
    assert not gc.black_wins
    assert not gc.tie
    assert gc.winning_num == 0
    assert not gc.end_game
    assert gc.print_turn

# No need to test update method since it's graphics display only


def test_update_terminal():
    '''Test update_terminal method to make sure end_game
    gets changed to True
    '''
    gc = GameController(800, 800)
    gc.white_wins = True
    gc.update_terminal()
    assert gc.end_game
