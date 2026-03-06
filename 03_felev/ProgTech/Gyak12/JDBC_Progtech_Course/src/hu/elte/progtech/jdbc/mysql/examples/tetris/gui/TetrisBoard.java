package hu.elte.progtech.jdbc.mysql.examples.tetris.gui;

import hu.elte.progtech.jdbc.mysql.examples.tetris.model.Direction;
import hu.elte.progtech.jdbc.mysql.examples.tetris.model.GameConstants;
import hu.elte.progtech.jdbc.mysql.examples.tetris.model.TetrisLogic;
import hu.elte.progtech.jdbc.mysql.examples.tetris.model.score.ScoreBoard;
import hu.elte.progtech.jdbc.mysql.examples.tetris.model.tetromino.Tetromino;
import hu.elte.progtech.jdbc.mysql.examples.tetris.model.tetromino.TetrominoShape;

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
        setFocusable(true);

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

        String name = JOptionPane.showInputDialog(null, "What is your name? ");
        if (name == null || name.trim().isEmpty()) {
            name = "Anonymous";
        }
        tetrisLogic.newGame(GameConstants.NORMAL_ROW_COUNT, GameConstants.NORMAL_COL_COUNT, name);

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
        // grid background
        graphics2D.setColor(TetrisUIConstants.GRID_COLOR);
        graphics2D.fill(TetrisUIConstants.GRID_RECTANGLE);

        // the blocks dropped in the grid
        int row = tetrisLogic.getRow();
        int column = tetrisLogic.getColumn();
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                int value = tetrisLogic.getGridValue(r, c);
                if (value > GameConstants.EMPTY) {
                    drawSquare(graphics2D, value, r, c);
                }
            }
        }

        // the borders of grid and preview panel
        graphics2D.setStroke(TetrisUIConstants.LARGE_STROKE);
        graphics2D.setColor(TetrisUIConstants.GRID_BORDER_COLOR);
        graphics2D.draw(TetrisUIConstants.GRID_RECTANGLE);
        graphics2D.draw(TetrisUIConstants.PREVIEW_RECTANGLE);

        drawScoreBoard(graphics2D);
        drawPreview(graphics2D);
    }

    private void drawScoreBoard(Graphics2D graphics2D) {
        ScoreBoard scoreBoard = tetrisLogic.getScoreBoard();
        int x = TetrisUIConstants.SCORE_POS_X;
        int y = TetrisUIConstants.SCORE_POS_Y;
        graphics2D.setColor(TetrisUIConstants.TEXT_COLOR);
        graphics2D.setFont(TetrisUIConstants.SMALL_FONT);
        graphics2D.drawString(String.format("High score %6d", scoreBoard.getTopScore()), x, y);
        graphics2D.drawString(String.format("Lines      %6d", scoreBoard.getLines()), x, y + 30);
        graphics2D.drawString(String.format("Score      %6d", scoreBoard.getScore()), x, y + 60);
    }

    private void drawPreview(Graphics2D graphics2D) {
        int minX = 5, minY = 5, maxX = 0, maxY = 0;
        Tetromino nextTetromino = tetrisLogic.getNextTetromino();
        for (int[] p : nextTetromino.getCoords()) {
            minX = Math.min(minX, p[0]);
            minY = Math.min(minY, p[1]);
            maxX = Math.max(maxX, p[0]);
            maxY = Math.max(maxY, p[1]);
        }
        double cx = TetrisUIConstants.PREVIEW_CENTER_X - ((minX + maxX + 1) / 2.0 * TetrisUIConstants.BLOCK_SIZE);
        double cy = TetrisUIConstants.PREVIEW_CENTER_Y - ((minY + maxY + 1) / 2.0 * TetrisUIConstants.BLOCK_SIZE);

        graphics2D.translate(cx, cy);

        TetrominoShape tetrominoShape = nextTetromino.getShape();
        for (int[] p : tetrominoShape.getCoordinatesCopy())
            drawSquare(graphics2D, tetrominoShape.ordinal(), p[1], p[0]);
        graphics2D.translate(-cx, -cy);
    }

    private void drawFallingShape(Graphics2D g) {
        Tetromino fallingTetromino = tetrisLogic.getFallingTetromino();
        int idx = fallingTetromino.getShape().ordinal();
        for (int[] p : fallingTetromino.getCoords()) {
            drawSquare(g, idx, fallingTetromino.getRowPosition() + p[1],
                    fallingTetromino.getColumnPosition() + p[0]);
        }
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        drawUI(graphics2D);

        ScoreBoard scoreBoard = tetrisLogic.getScoreBoard();
        if (scoreBoard.isGameOver()) {
            drawStartScreen(graphics2D);
        } else {
            drawFallingShape(graphics2D);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void shapeHasLanded() {
        Tetromino fallingTetromino = tetrisLogic.getFallingTetromino();
        tetrisLogic.addShape(fallingTetromino);
        ScoreBoard scoreBoard = tetrisLogic.getScoreBoard();
        if (fallingTetromino.getRowPosition() < 2) {
            scoreBoard.setGameOver();
            stop();
            tetrisLogic.saveScoreIfGreaterThan0();
            scoreBoard.setTopScore();
        } else {
            scoreBoard.addLines(tetrisLogic.removeLines());
        }
        tetrisLogic.selectTetromino();
    }

    private final Action oneGameCycleAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ScoreBoard scoreBoard = tetrisLogic.getScoreBoard();
            if (!scoreBoard.isGameOver()) {
                Tetromino fallingTetromino = tetrisLogic.getFallingTetromino();
                if (tetrisLogic.canMove(fallingTetromino, Direction.DOWN)) {
                    tetrisLogic.move(Direction.DOWN);
                } else {
                    shapeHasLanded();
                }
                repaint();
            }
        }
    };

    private final KeyListener tetrisKeyListener = new KeyAdapter() {

        private boolean fastDown = false;

        @Override
        public void keyPressed(KeyEvent e) {

            final ScoreBoard scoreBoard = tetrisLogic.getScoreBoard();
            if (scoreBoard.isGameOver()) {
                return;
            }

            Tetromino fallingTetromino = tetrisLogic.getFallingTetromino();

            switch (e.getKeyCode()) {

                case KeyEvent.VK_UP:
                    if (tetrisLogic.canRotate(fallingTetromino))
                        tetrisLogic.rotate(fallingTetromino);
                    break;

                case KeyEvent.VK_LEFT:
                    if (tetrisLogic.canMove(fallingTetromino, Direction.LEFT))
                        tetrisLogic.move(Direction.LEFT);
                    break;

                case KeyEvent.VK_RIGHT:
                    if (tetrisLogic.canMove(fallingTetromino, Direction.RIGHT))
                        tetrisLogic.move(Direction.RIGHT);
                    break;

                case KeyEvent.VK_DOWN:
                    if (!fastDown) {
                        fastDown = true;
                        while (tetrisLogic.canMove(fallingTetromino, Direction.DOWN)) {
                            tetrisLogic.move(Direction.DOWN);
                        }
                        shapeHasLanded();
                    }
            }
            repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            fastDown = false;
        }
    };
}