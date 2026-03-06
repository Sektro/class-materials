package hu.elte.progtech.snake.gui;

import hu.elte.progtech.snake.model.Direction;
import hu.elte.progtech.snake.model.GameConstants;
import hu.elte.progtech.snake.model.SnakeLogic;
import hu.elte.progtech.snake.model.score.ScoreBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SnakeBoard extends JPanel {

    private final SnakeLogic snakelogic;
    private Timer timer;


    public SnakeBoard(SnakeLogic snakelogic) {
        this.snakelogic = snakelogic;
        setPreferredSize(new Dimension(SnakeUIConstants.GAME_WIDTH, SnakeUIConstants.GAME_HEIGHT));
        setBackground(SnakeUIConstants.BACKGROUND_COLOR);
        setFocusable(true); // kell a keylistener miatt

        startNewGame();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ScoreBoard scoreBoard = snakelogic.getScoreBoard();
                if (scoreBoard.isGameOver()) {
                    startNewGame();
                    repaint();
                }
            }
        });
        addKeyListener(snakeKeyListener);

    }

    private void startNewGame() {
        stop();

        String name = JOptionPane.showInputDialog(null, "What is your name? ");
        if (name == null || name.trim().isEmpty()) {
            name = "Anonymous";
        }
        snakelogic.newGame(GameConstants.BOARD_SIZE, name);

        timer = new Timer(1000, oneGameCycleAction);
        timer.start();
    }
    private void stop() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

    private final Action oneGameCycleAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ScoreBoard scoreBoard = snakelogic.getScoreBoard();
            if (!scoreBoard.isGameOver()) {
                if (snakelogic.move()) {
                    endOfGame();
                }
                repaint();
            }
        }
    };

    private void endOfGame() {
        ScoreBoard scoreBoard = snakelogic.getScoreBoard();
        scoreBoard.setGameOver();
        stop();
        snakelogic.saveScoreIfGreaterThan0();
        scoreBoard.setTopScore();
    }



    private final KeyListener snakeKeyListener = new KeyAdapter() {

        @Override
        public void keyPressed(KeyEvent e) {
            final ScoreBoard scoreBoard = snakelogic.getScoreBoard();
            if (scoreBoard.isGameOver()) {
                return;
            }

            switch (e.getKeyCode()) {
                case KeyEvent.VK_W -> {
                    if (snakelogic.canMove(Direction.UP)) {
                        snakelogic.changeDirections(Direction.UP);
                    }
                    break;
                }
                case KeyEvent.VK_A -> {
                    if (snakelogic.canMove(Direction.LEFT)) {
                        snakelogic.changeDirections(Direction.LEFT);
                    }
                    break;
                }
                case KeyEvent.VK_D -> {
                    if (snakelogic.canMove(Direction.RIGHT)) {
                        snakelogic.changeDirections(Direction.RIGHT);
                    }
                    break;
                }
                case KeyEvent.VK_S -> {
                    if (snakelogic.canMove(Direction.DOWN)) {
                        snakelogic.changeDirections(Direction.DOWN);
                    }
                    break;
                }
            }
            repaint();
        }
    };
}
