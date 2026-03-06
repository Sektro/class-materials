package hu.elte.progtech.snake.gui;

import hu.elte.progtech.snake.model.SnakeLogic;

import javax.swing.*;
import java.awt.*;

public class SnakeFrame extends JFrame {

    public SnakeFrame(SnakeLogic snakeLogic) {
        setTitle("Snake");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().add(new SnakeBoard(snakeLogic), BorderLayout.CENTER);
        pack();

        setLocationRelativeTo(null);
    }
}