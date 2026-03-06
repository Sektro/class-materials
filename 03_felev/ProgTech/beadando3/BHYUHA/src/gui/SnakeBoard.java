package hu.elte.progtech.snake.gui;

import hu.elte.progtech.snake.model.Direction;
import hu.elte.progtech.snake.model.GameConstants;
import hu.elte.progtech.snake.model.SnakeLogic;
import hu.elte.progtech.snake.model.score.ScoreBoard;
import hu.elte.progtech.snake.persistence.dao.HighScoreDao;
import hu.elte.progtech.snake.persistence.entity.HighScore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showMessageDialog;

public class SnakeBoard extends JPanel {
    private SnakeFrame frame;
    private JPanel playingField;
    private BoardPiece[][] field;
    private final SnakeLogic snakelogic;
    private Timer gameTimer;
    private Timer simpleTimer;
    private JLabel time;
    private JLabel score;
    private JPanel timescore;
    private int seconds;

    /**
     * Constructor of SnakeBoard
     *
     * Constructs the UI for the game using a newly constructed logic class as the parameter.
     * This is also the function where we make the buttons and keys necessary for the game functioning
     * @param snakelogic
     */
    public SnakeBoard(SnakeLogic snakelogic) {
        this.snakelogic = snakelogic;
        setBackground(SnakeUIConstants.BACKGROUND_COLOR);
        setFocusable(true);

        frame = new SnakeFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(SnakeUIConstants.GAME_WIDTH,SnakeUIConstants.GAME_HEIGHT));

        playingField = new JPanel(new GridLayout(GameConstants.BOARD_SIZE,GameConstants.BOARD_SIZE));
        field = new BoardPiece[GameConstants.BOARD_SIZE][GameConstants.BOARD_SIZE];
        startNewGame();

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu gameMenu = new JMenu("Menu");
        menuBar.add(gameMenu);
        JMenuItem newGameItem = new JMenuItem("New Game");
        newGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().remove(playingField);
                playingField = new JPanel(new GridLayout(GameConstants.BOARD_SIZE,GameConstants.BOARD_SIZE));
                startNewGame();
                frame.pack();
            }
        });
        gameMenu.add(newGameItem);
        JMenuItem top10 = getjMenuItem(snakelogic);
        top10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HighScoreDao highScoreDao = snakelogic.getHighScoreDao();
                try {
                    ArrayList<HighScore> top10list = highScoreDao.getTop10();
                    String message = "";
                    for (HighScore hs : top10list) {
                        message += hs.getName() + " : " + hs.getScore() + "\n";
                    }
                    showMessageDialog(null, message);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        gameMenu.add(top10);
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        gameMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

        timescore = new JPanel();
        score = new JLabel( "score: 0");
        score.setHorizontalAlignment(JLabel.LEFT);
        timescore.add(score);
        time = new JLabel("0:00");
        time.setHorizontalAlignment(JLabel.RIGHT);
        timescore.add(time);
        frame.add(BorderLayout.SOUTH,timescore);






        frame.pack();
        frame.setVisible(true);

        KeyListener snakeKeyListener = new KeyAdapter() {

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
        frame.setFocusable(true);
        frame.requestFocus();
        frame.addKeyListener(snakeKeyListener);

    }

    private static JMenuItem getjMenuItem(SnakeLogic snakelogic) {
        JMenuItem top10 = new JMenuItem("Top 10");
        top10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HighScoreDao highScoreDao = snakelogic.getHighScoreDao();
                try {
                    ArrayList<HighScore> top10list = highScoreDao.getTop10();
                    String message = "";
                    for (HighScore hs : top10list) {
                        message += hs.getName() + " : " + hs.getScore() + "\n";
                    }
                    showMessageDialog(null, message);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        return top10;
    }

    private void startNewGame() {
        stop();

        String name = JOptionPane.showInputDialog(null, "What is your name? ");
        if (name == null || name.trim().isEmpty()) {
            name = "Anonymous";
        }
        snakelogic.newGame(GameConstants.BOARD_SIZE, name);
        for (int i = 0; i < GameConstants.BOARD_SIZE; ++i) {
            for (int j = 0; j < GameConstants.BOARD_SIZE; ++j) {
                BoardPiece tile = new BoardPiece(j,i);
                tile.setPreferredSize(new Dimension(SnakeUIConstants.TILE_SIZE, SnakeUIConstants.TILE_SIZE));
                field[i][j] = tile;
                playingField.add(tile);
            }
        }
        painting();
        frame.getContentPane().add(BorderLayout.CENTER,playingField);



        gameTimer = new Timer(GameConstants.GAME_SPEED, oneGameCycleAction);
        gameTimer.start();
        seconds = 0;
        simpleTimer = new Timer(1000, showTime);
        simpleTimer.start();
    }
    private void stop() {
        if (gameTimer != null && gameTimer.isRunning()) {
            gameTimer.stop();
        }
        if (simpleTimer != null && simpleTimer.isRunning()) {
            simpleTimer.stop();
        }
    }
    private void painting() {
        for (int i = 0; i < GameConstants.BOARD_SIZE; ++i) {
            for (int j = 0; j < GameConstants.BOARD_SIZE; ++j) {
                field[i][j].changeColors();
            }
        }
    }
    private final Action showTime  = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            time.setText(convertSeconds(seconds));
            ++seconds;
        }
    };
    private String convertSeconds(int sec) {
        if (sec < 10) {
            return "0:0" + sec;
        }
        if (sec < 60) {
            return "0:" + sec;
        }
        int minutes = sec % 60;
        int seconds = sec - ((sec % 60) * 60);
        if (seconds < 10) {
            return minutes + ":0" + seconds;
        }
        return minutes + ":" + seconds;
    }

    private final Action oneGameCycleAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ScoreBoard scoreBoard = snakelogic.getScoreBoard();
            if (!scoreBoard.isGameOver()) {
                if (snakelogic.move()) {
                    showMessageDialog(null, "Game Over!");
                    endOfGame();
                }
                painting();
                repaint();
            }
            score.setText("score: " + scoreBoard.getScore());
        }
    };

    private void endOfGame() {
        ScoreBoard scoreBoard = snakelogic.getScoreBoard();
        scoreBoard.setGameOver();
        stop();
        snakelogic.saveScoreIfGreaterThan0();
        scoreBoard.setTopScore();
    }

    private class BoardPiece extends JButton {
        private final int x;
        private final int y;

        private BoardPiece(int x,int y) {
            super();
            this.x = x;
            this.y = y;
        }
        private void changeColors() {
            switch (snakelogic.getGridValue(y,x)) {
                case GameConstants.EMPTY -> this.setBackground(SnakeUIConstants.EMPTY_BACKGROUND);
                case GameConstants.BORDER -> this.setBackground(SnakeUIConstants.BORDER_BACKGROUND);
                case GameConstants.APPLE -> this.setBackground(SnakeUIConstants.APPLE_BACKGROUND);
                case GameConstants.SNAKE -> this.setBackground(SnakeUIConstants.SNAKE_BACKGROUND);
                case GameConstants.STONE -> this.setBackground(SnakeUIConstants.STONE_BACKGROUND);
            }
        }
    }

}
