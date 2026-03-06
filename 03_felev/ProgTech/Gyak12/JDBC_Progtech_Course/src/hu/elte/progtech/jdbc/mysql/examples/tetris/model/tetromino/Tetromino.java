package hu.elte.progtech.jdbc.mysql.examples.tetris.model.tetromino;

public class Tetromino {

    private final TetrominoShape shape;
    private int rowPosition;
    private int columnPosition;

    private int[][] coords;

    public Tetromino(TetrominoShape shape) {
        this.shape = shape;
        reset();
    }

    public void reset() {
        coords = shape.getCoordinatesCopy();
    }

    public void rotate() {
        if (shape != TetrominoShape.O_SHAPE) {
            for (int[] row : coords) {
                int tmp = row[0];
                row[0] = row[1];
                row[1] = -tmp;
            }
        }
    }

    public int[] getClonedCoordinate(int i) {
        return coords[i].clone();
    }

    public TetrominoShape getShape() {
        return shape;
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public int getColumnPosition() {
        return columnPosition;
    }

    public int[][] getCoords() {
        return coords;
    }

    public void setRowPosition(int rowPosition) {
        this.rowPosition = rowPosition;
    }

    public void setColumnPosition(int columnPosition) {
        this.columnPosition = columnPosition;
    }

    public void modifyRowPosition(int dx) {
        rowPosition += dx;
    }

    public void modifyColumnPosition(int dy) {
        columnPosition += dy;
    }
}