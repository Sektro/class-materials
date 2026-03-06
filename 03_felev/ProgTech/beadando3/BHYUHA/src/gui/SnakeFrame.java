package hu.elte.progtech.snake.gui;

import hu.elte.progtech.snake.model.SnakeLogic;

import javax.swing.*;
import java.awt.*;

public class SnakeFrame extends JFrame {

    /**
     * Constructor of SnakeFrame
     */
    public SnakeFrame() {
        setTitle("Snake");
        setResizable(false);
        pack();

        setLocationRelativeTo(null);
    }
}