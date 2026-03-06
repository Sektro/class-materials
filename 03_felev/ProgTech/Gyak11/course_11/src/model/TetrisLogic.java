package model;

import model.score.ScoreBoard;
import model.tetromino.Tetromino;
import model.tetromino.TetrominoShape;

import java.util.Arrays;

public final class TetrisLogic {
    private int row;
    private int column;

    private int[][] grids;

    private Tetromino fallingTetromino;
    private Tetromino nextTetromino;

    private ScoreBoard scoreBoard;

    public void newGame(int row, int column){
        this.row = row;
        this.column = column;

        grids = new int[row][column];
        initGrid();

        scoreBoard = new ScoreBoard();

        nextTetromino = new Tetromino(TetrominoShape.getRandomTetrominoType());

        selectTetromino();
        scoreBoard.reset();
    }

    public boolean canRotate(Tetromino tetromino) {
        TetrominoShape tetrominoShape = tetromino.getShape();
        if (tetrominoShape == TetrominoShape.O_SHAPE) {
            return false;
        }

        int[][] pos = new int[4][2];
        for (int i = 0; i < pos.length; i++) {
            pos[i] = tetromino.getClonedCoordinate(i);
        }

        for (int[] row : pos) {
            int tmp = row[0];
            row[0] = row[1];
            row[1] = -tmp;
        }

        for (int[] p : pos) {
            int newCol = fallingTetromino.getColumnPosition() + p[0];
            int newRow = fallingTetromino.getRowPosition() + p[1];
            if (grids[newRow][newCol] != GameConstants.EMPTY) {
                return false;
            }
        }
        return true;
    }

    public void rotate(Tetromino tetromino) {
        tetromino.rotate();
    }

    public void move(Direction direction) {
        if(fallingTetromino != null) {
            fallingTetromino.modifyRowPosition(direction.getY());
            fallingTetromino.modifyColumnPosition(direction.getX());
        }
    }

    public boolean canMove(Tetromino tetromino, Direction direction) {
        for (int[] p : tetromino.getCoords()) {
            int newCol = fallingTetromino.getColumnPosition() + direction.getX() + p[0];
            int newRow = fallingTetromino.getRowPosition() + direction.getY() + p[1];
            if (grids[newRow][newCol] != GameConstants.EMPTY) {
                return false;
            }
        }
        return true;
    }

    public int removeLines() {
        int count = 0;
        for (int r = 0; r < row - 1; r++) {
            for (int c = 1; c < column - 1; c++) {
                if (grids[r][c] == GameConstants.EMPTY) {
                    break;
                }

                if (c == column - 2) {
                    count++;
                    removeLine(r);
                }
            }
        }
        return count;
    }

    private void removeLine(int line) {
        for (int c = 0; c < column; c++) {
            grids[line][c] = GameConstants.EMPTY;
        }

        for (int c = 0; c < column; c++) {
            for (int r = line; r > 0; r--) {
                grids[r][c] = grids[r - 1][c];
            }
        }
    }

    public void addShape(Tetromino tetromino) {
        TetrominoShape tetrominoShape = tetromino.getShape();
        for (int[] p : tetromino.getCoords()) {
            grids[fallingTetromino.getRowPosition() + p[1]][fallingTetromino.getColumnPosition() + p[0]] =
                    tetrominoShape.ordinal();
        }
    }

    public void selectTetromino(){
        fallingTetromino = nextTetromino;
        fallingTetromino.setRowPosition(1);
        fallingTetromino.setColumnPosition(5);

        nextTetromino = new Tetromino(TetrominoShape.getRandomTetrominoType());
    }

    private void initGrid() {
        for (int i = 0; i < row; ++i) {
            Arrays.fill(grids[i], GameConstants.EMPTY);
            for (int j = 0; j < column; ++j) {
                if (j == 0 || j == column - 1 || i == row - 1) {
                    grids[i][j] = GameConstants.BORDER;
                }
            }
        }
    }

    public Tetromino getFallingTetromino() {
        return fallingTetromino;
    }

    public Tetromino getNextTetromino() {
        return nextTetromino;
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getGridValue(int row, int col){
        return grids[row][col];
    }

}