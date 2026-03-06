package gui;

import model.TetrisLogic;

import javax.swing.*;
import java.awt.*;

public class TetrisFrame extends JFrame {

    public TetrisFrame(TetrisLogic tetrisLogic) {
        setTitle("Tetris");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().add(new TetrisBoard(tetrisLogic), BorderLayout.CENTER);
        pack();

        setLocationRelativeTo(null);
    }
}