package gui;

import model.Direction;
import model.GameConstants;
import model.TetrisLogic;
import model.score.ScoreBoard;
import model.tetromino.Tetromino;
import model.tetromino.TetrominoShape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TetrisBoard extends JPanel {

    private final TetrisLogic tetrisLogic;

    private Timer timer;

    public TetrisBoard(TetrisLogic tetrisLogic) {
        this.tetrisLogic = tetrisLogic;
        setPreferredSize(new Dimension(TetrisUIConstants.GAME_WIDTH, TetrisUIConstants.GAME_HEIGHT));
        setBackground(TetrisUIConstants.BACKGROUND_COLOR);
        setFocusable(true); // kell a keylistener miatt

        startNewGame();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ScoreBoard scoreBoard = tetrisLogic.getScoreBoard();
                if (scoreBoard.isGameOver()) {
                    startNewGame();
                    repaint();
                }
            }
        });

        addKeyListener(tetrisKeyListener);
    }

    private void startNewGame() {
        stop();
        tetrisLogic.newGame(GameConstants.NORMAL_ROW_COUNT, GameConstants.NORMAL_COL_COUNT);
        timer = new Timer(700, oneGameCycleAction);
        timer.start();
    }

    private void stop() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

    private void drawStartScreen(Graphics2D graphics2D) {
        graphics2D.setFont(TetrisUIConstants.MAIN_FONT);

        graphics2D.setColor(TetrisUIConstants.TITLE_BG_COLOR);
        graphics2D.fill(TetrisUIConstants.TITLE_RECTANGLE);
        graphics2D.fill(TetrisUIConstants.CLICK_RECTANGLE);

        graphics2D.setColor(TetrisUIConstants.TEXT_COLOR);
        graphics2D.drawString("Tetris", TetrisUIConstants.TITLE_POS_X, TetrisUIConstants.TITLE_POS_Y);

        graphics2D.setFont(TetrisUIConstants.SMALL_FONT);
        graphics2D.drawString("Click to start", TetrisUIConstants.CLICK_POS_X, TetrisUIConstants.CLICK_POS_Y);
    }

    private void drawSquare(Graphics2D g, int colorIndex, int r, int c) {
        g.setColor(TetrisUIConstants.COLORS[colorIndex]);
        int x = TetrisUIConstants.LEFT_MARGIN + c * TetrisUIConstants.BLOCK_SIZE;
        int y = TetrisUIConstants.TOP_MARGIN + r * TetrisUIConstants.BLOCK_SIZE;
        g.fillRect(x, y, TetrisUIConstants.BLOCK_SIZE, TetrisUIConstants.BLOCK_SIZE);

        g.setStroke(TetrisUIConstants.SMALL_STROKE);
        g.setColor(TetrisUIConstants.SQUARE_BORDER);
        g.drawRect(x, y, TetrisUIConstants.BLOCK_SIZE, TetrisUIConstants.BLOCK_SIZE);
    }

    private void drawUI(Graphics2D graphics2D) {
        //TODO: finish it
    }

    private void drawScoreBoard(Graphics2D graphics2D) {
        //TODO: finish it
    }

    private void drawPreview(Graphics2D graphics2D) {
        //TODO: finish it
    }

    private void drawFallingShape(Graphics2D g) {
        //TODO: finish it
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        drawUI(graphics2D);

        //TODO: finish it

        Toolkit.getDefaultToolkit().sync();
    }

    private void shapeHasLanded() {
        //TODO: finish it
    }

    private final Action oneGameCycleAction = new AbstractAction() {
        //TODO: finish it
    };

    private final KeyListener tetrisKeyListener = new KeyAdapter() {

        private boolean down;

        @Override
        public void keyPressed(KeyEvent e) {
            final ScoreBoard scoreBoard = tetrisLogic.getScoreBoard();
            if (scoreBoard.isGameOver()) {
                return;
            }

            Tetromino fallingTetromino = tetrisLogic.getFallingTetromino();

            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP -> {
                    if (tetrisLogic.canRotate(fallingTetromino)) {
                        tetrisLogic.rotate(fallingTetromino);
                    }
                    break;
                }
                case KeyEvent.VK_LEFT -> {
                    if (tetrisLogic.canMove(fallingTetromino,Direction.LEFT)) {
                        tetrisLogic.move(Direction.LEFT);
                    }
                    break;
                }
                case KeyEvent.VK_RIGHT -> {
                    if (tetrisLogic.canMove(fallingTetromino,Direction.RIGHT)) {
                        tetrisLogic.move(Direction.RIGHT);
                    }
                    break;
                }
                case KeyEvent.VK_DOWN -> {
                    if (!down) {
                        down = true;
                        while (tetrisLogic.canMove(fallingTetromino, Direction.DOWN)) {
                            tetrisLogic.move(Direction.DOWN);
                        }
                        shapeHasLanded();
                    }
                    break;
                }
            }
            repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            down = false;
        }
    };
}