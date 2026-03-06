package hu.elte.progtech.snake;

import hu.elte.progtech.snake.gui.SnakeFrame;
import hu.elte.progtech.snake.model.SnakeLogic;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SnakeFrame(new SnakeLogic()).setVisible(true));
    }
}//jani