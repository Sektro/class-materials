package pelda.logic;

import java.util.Random;

public class GameLogic {

    private int [][] fields;
    private int size;
    private int steps;

    public void newGame(int size) {
        this.size = size;
        this.steps = 0;
        this.fields = new int[size][size];

        fillFields();
    }

    public int getSize() {
        return size;
    }

    public int getSteps() {
        return steps;
    }

    private void fillFields() {
        Random random = new Random();
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                fields[i][j] = random.nextInt(3);
            }
        }
    }

    public int getFieldsValue(int row, int column) {
        return fields[row][column];
    }

    public void changeFieldValues(int row, int column) {
        changeSingleFieldValue(row,column);
        changeSingleFieldValue(row-1,column);
        changeSingleFieldValue(row+1,column);
        changeSingleFieldValue(row,column-1);
        changeSingleFieldValue(row,column+1);
        steps++;
    }

    public void changeSingleFieldValue(int row, int column) {
        if(!(row < 0 || column < 0 || row >= size || column >= size)) {
            fields[row][column]++;
            fields[row][column] %= 3;
        }
    }

    public boolean isGameEnd() {
        int firstFieldColor = fields[0][0];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (fields[i][j] != firstFieldColor) {
                    return false;
                }
            }
        }
        return true;
    }

}
