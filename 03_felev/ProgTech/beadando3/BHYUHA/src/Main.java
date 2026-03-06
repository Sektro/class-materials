package hu.elte.progtech.snake;

import hu.elte.progtech.snake.gui.SnakeBoard;
import hu.elte.progtech.snake.model.SnakeLogic;

public class Main {
    public static void main(String[] args) {
        SnakeBoard snakeBoard = new SnakeBoard(new SnakeLogic());
    }
}//jani