from game_manager import GameManager
from game_controller import GameController
from board import Board


def test_constructor():
    '''Test initialization of GameController
    '''
    gc = GameController(400, 400)
    bd = Board(0, 100, 0, 400, 400, 0, 4, 100, 4, gc)
    gm = GameManager(bd)
    assert gm.player_turn
    assert gm.board == bd
    assert gm.print_turn
    assert gm.prompt_name


def test_is_legal_move():
    '''Test is_legal_move method to make sure legal and non-legal moves
    are handeled correctly.
    '''
    gc = GameController(400, 400)
    bd = Board(0, 100, 0, 400, 400, 0, 4, 100, 4, gc)
    gm = GameManager(bd)
    bd.tile.get_all_tiles()
    bd.tile.starting_four_tiles()
    assert not gm.is_legal_move(0, 0)
    assert gm.is_legal_move(0, 1) == [[1, 1]]
    assert gm.is_legal_move(1, 0) == [[1, 1]]
    gm.player_turn = False
    assert not gm.is_legal_move(1, 1)
    assert gm.is_legal_move(2, 0) == [[2, 1]]
    assert gm.is_legal_move(3, 1) == [[2, 1]]
    assert gm.is_legal_move(0, 2) == [[1, 2]]
    assert gm.is_legal_move(1, 3) == [[1, 2]]


def test_real_space():
    '''Test real_space method to make sure spaces that are off
    the board are not counted as real spaces.
    '''
    gc = GameController(400, 400)
    bd = Board(0, 100, 0, 400, 400, 0, 4, 100, 4, gc)
    gm = GameManager(bd)
    assert gm.real_space(1, 2)
    assert not gm.real_space(4, 2)
    assert gm.real_space(0, 0)
    assert not gm.real_space(-1, 0)


def test_flip_tiles():
    gc = GameController(400, 400)
    bd = Board(0, 100, 0, 400, 400, 0, 4, 100, 4, gc)
    gm = GameManager(bd)
    bd.tile.get_all_tiles()
    gm.flip_tiles([[1, 1], [0, 1]], 0)
    assert bd.disks[1][1].fill_color == 'black'
    assert bd.disks[0][1].fill_color == 'black'
    assert bd.disks[0][0].fill_color == 'green'


def test_ai_move_decision():
    '''Test if the computer makes the correct deicion for
    certain tile situations on board.
    '''
    gc = GameController(400, 400)
    bd = Board(0, 100, 0, 400, 400, 0, 4, 100, 4, gc)
    gm = GameManager(bd)
    bd.tile.get_all_tiles()
    bd.tile.starting_four_tiles()
    # Change space (2, 2) to black and (2, 3) to white.
    # Now best move should be space (2, 0)
    bd.tile.change_color(2, 2, 0)
    bd.tile.change_color(2, 3, 255)
    gm.player_turn = False
    assert gm.ai_move_decision() == (2, 0)
    bd.tile.change_color(2, 0, 0)
    bd.tile.change_color(0, 2, 255)
    assert gm.ai_move_decision() == (3, 2)
    # Change all tiles to black so there are no legal moves.
    # method should return False
    bd.tile.change_color(1, 1, 0)
    bd.tile.change_color(0, 2, 0)
    bd.tile.change_color(2, 3, 0)
    assert not gm.ai_move_decision()


def test_player_make_move():
    '''Test that the player move is legal and the game is not
    over
    '''
    gc = GameController(400, 400)
    bd = Board(0, 100, 0, 400, 400, 0, 4, 100, 4, gc)
    gm = GameManager(bd)
    bd.tile.get_all_tiles()
    bd.tile.starting_four_tiles()
    # Check that a legal move causes player turn to change to False
    # and the correct tiles are on the board
    gm.player_turn = True
    gm.player_make_move(100, 0)
    assert bd.disks[1][0].fill_color == 'black'
    assert bd.disks[1][1].fill_color == 'black'
    assert not gm.player_turn
    assert gm.print_turn
    # Test that if move is not legal, nothing changes
    gm.player_turn = True
    gm.player_make_move(0, 0)
    assert gm.player_turn
    assert bd.disks[0][0].fill_color == 'green'
    # Test what happens when there are no more legal moves for player
    # but still legal moves for computer
    bd.tile.change_color(2, 2, 0)
    bd.tile.change_color(1, 3, 255)
    gm.player_make_move(0, 0)
    assert not gm.player_turn
    assert gm.print_turn
    assert not bd.end_game
    # Test when neither ai nor player has a move left
    gm.player_turn = True
    bd.tile.change_color(1, 3, 0)
    gm.player_make_move(0, 0)
    assert bd.end_game


def test_ai_make_move():
    '''Test ai_make_move method to make sure the move is
    being made and that game ends when appropriate 
    '''
    gc = GameController(400, 400)
    bd = Board(0, 100, 0, 400, 400, 0, 4, 100, 4, gc)
    gm = GameManager(bd)
    bd.tile.get_all_tiles()
    bd.tile.starting_four_tiles()
    # Check ai makes the right move when able
    bd.tile.change_color(2, 2, 0)
    bd.tile.change_color(2, 3, 255)
    gm.player_turn = False
    gm.ai_make_move()
    assert bd.disks[2][0].fill_color == 'white'
    assert bd.disks[2][1].fill_color == 'white'
    assert bd.disks[2][2].fill_color == 'white'
    assert gm.player_turn
    # Check AI passes turn when no moves
    gc = GameController(400, 400)
    bd = Board(0, 100, 0, 400, 400, 0, 4, 100, 4, gc)
    gm = GameManager(bd)
    bd.tile.get_all_tiles()
    bd.tile.starting_four_tiles()
    bd.tile.change_color(2, 2, 0)
    bd.tile.change_color(3, 3, 0)
    bd.tile.change_color(3, 1, 255)
    bd.tile.change_color(1, 3, 255)
    gm.player_turn = False
    gm.ai_make_move()
    assert gm.player_turn
    assert not bd.end_game
    # Check game ends when AI and Player can not make a move
    bd.tile.change_color(0, 0, 0)
    bd.tile.change_color(1, 0, 0)
    bd.tile.change_color(0, 1, 0)
    gm.player_turn = False
    gm.ai_make_move()
    assert gm.player_turn
    assert bd.end_game


def test_update_turn():
    '''Test update_turn to make sure method is called correctly
    '''
    gc = GameController(400, 400)
    bd = Board(0, 100, 0, 400, 400, 0, 4, 100, 4, gc)
    gm = GameManager(bd)
    bd.tile.get_all_tiles()
    bd.tile.starting_four_tiles()
    gm.update_turn()
    assert gm.player_turn
    assert not gm.print_turn
    # Can't test when bd.done = True since input method gives no javax module
    # error


def test_highest_score():
    '''Test that specific scores change the order in the file
    and that lower scores are only appended. Use scores_test.txt as test file.
    '''
    gc = GameController(400, 400)
    bd = Board(0, 100, 0, 400, 400, 0, 4, 100, 4, gc)
    gm = GameManager(bd)
    f = open('scores_test.txt', 'w').close()
    gm.highest_score('Test Name', 35, 'scores_test.txt')
    f = open('scores_test.txt', 'r')
    top = f.readlines()[0]
    assert top == 'Test Name 35\n'
    f.close()
    gm.highest_score('Test Name 2', 20, 'scores_test.txt')
    f = open('scores_test.txt', 'r')
    lst = f.readlines()
    top = lst[0]
    second = lst[1]
    assert top == 'Test Name 35\n'
    assert second == 'Test Name 2 20\n'
    f.close()
    gm.highest_score('Test Name 3', 36, 'scores_test.txt')
    f = open('scores_test.txt', 'r')
    lst = f.readlines()
    top = lst[0]
    second = lst[1]
    last = lst[2]
    assert top == 'Test Name 3 36\n'
    assert second == 'Test Name 35\n'
    assert last == 'Test Name 2 20\n'
