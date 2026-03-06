package hu.elte.progtech.jdbc.mysql.examples.tetris;

import hu.elte.progtech.jdbc.mysql.examples.tetris.gui.TetrisFrame;
import hu.elte.progtech.jdbc.mysql.examples.tetris.model.TetrisLogic;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TetrisFrame(new TetrisLogic()).setVisible(true));
    }
}
