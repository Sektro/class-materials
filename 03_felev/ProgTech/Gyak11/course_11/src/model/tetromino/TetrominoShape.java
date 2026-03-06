package model.tetromino;

import java.util.Random;

public enum TetrominoShape {
    /* Amennyiben koordináta rendszerként képzeljük el (Az ábrán a 3. sor 3. a (0,0) pont)
     *  [ ][ ][ ][ ][ ]
     *  [ ][X][ ][ ][ ]
     *  [ ][X][X][ ][ ]
     *  [ ][ ][X][ ][ ]
     *  [ ][ ][ ][ ][ ]
     */
    S_SHAPE(new int[][]{{0, -1}, {0, 0}, {-1, 0}, {-1, 1}}),
    /*
     *  [ ][ ][ ][ ][ ]
     *  [ ][ ][ ][X][ ]
     *  [ ][ ][X][X][ ]
     *  [ ][ ][X][ ][ ]
     *  [ ][ ][ ][ ][ ]
     */
    Z_SHAPE(new int[][]{{0, -1}, {0, 0}, {1, 0}, {1, 1}}),
    /*
     *  [ ][ ][X][ ][ ]
     *  [ ][ ][X][ ][ ]
     *  [ ][ ][X][ ][ ]
     *  [ ][ ][X][ ][ ]
     *  [ ][ ][ ][ ][ ]
     */
    I_SHAPE(new int[][]{{0, -1}, {0, 0}, {0, 1}, {0, 2}}),
    /*
     *  [ ][ ][ ][ ][ ]
     *  [ ][ ][X][ ][ ]
     *  [ ][X][X][X][ ]
     *  [ ][ ][ ][ ][ ]
     *  [ ][ ][ ][ ][ ]
     */
    T_SHAPE(new int[][]{{-1, 0}, {0, 0}, {1, 0}, {0, 1}}),
    /*
     *  [ ][ ][ ][ ][ ]
     *  [ ][ ][X][X][ ]
     *  [ ][ ][X][X][ ]
     *  [ ][ ][ ][ ][ ]
     *  [ ][ ][ ][ ][ ]
     */
    O_SHAPE(new int[][]{{0, 0}, {1, 0}, {0, 1}, {1, 1}}),
    /*
     *  [ ][ ][ ][ ][ ]
     *  [ ][ ][X][ ][ ]
     *  [ ][ ][X][ ][ ]
     *  [ ][X][X][ ][ ]
     *  [ ][ ][ ][ ][ ]
     */
    J_SHAPE(new int[][]{{-1, -1}, {0, -1}, {0, 0}, {0, 1}}),
    /*
     *  [ ][ ][ ][ ][ ]
     *  [ ][ ][X][ ][ ]
     *  [ ][ ][X][ ][ ]
     *  [ ][ ][X][X][ ]
     *  [ ][ ][ ][ ][ ]
     */
    L_SHAPE(new int[][]{{1, -1}, {0, -1}, {0, 0}, {0, 1}});

    private final int[][] coordinates;

    TetrominoShape(int[][] coordinates) {
        this.coordinates = coordinates;
    }

    public int[][] getCoordinatesCopy() {
        int[][] result = new int[4][2];
        for (int i = 0; i < result.length; ++i) {
            System.arraycopy(coordinates[i], 0, result[i], 0, result[i].length);
        }
        return result;
    }

    public static TetrominoShape getRandomTetrominoType() {
        TetrominoShape[] values = values();

        Random random = new Random();

        int idx = Math.abs(random.nextInt()) % values.length;
        return values[idx];
    }
}