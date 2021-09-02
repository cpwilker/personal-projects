from board import Board
from game_controller import GameController
from game_manager import GameManager
import time

R = 0
G = 100
B = 0
HEIGHT = 800
WIDTH = 800
STROKE = 0
WEIGHT = 4
NxN_SIZE = 8
SIZE = WIDTH/NxN_SIZE
delay = 200


gc = GameController(WIDTH, HEIGHT)
board = Board(R, G, B, HEIGHT, WIDTH, STROKE, WEIGHT, SIZE, NxN_SIZE, gc)
gm = GameManager(board)


def setup():
    size(HEIGHT, WIDTH)
    background(R, G, B)


def draw():
    global delay
    gm.update_turn()
    board.display()
    if not gm.player_turn:
        # temp_delay = delay
        if delay > 0:
            delay -= 1
        else:
            gm.ai_make_move()
            delay = 100
    gc.update()
    gc.update_terminal()


def mousePressed():
    gm.player_make_move(mouseX, mouseY)
