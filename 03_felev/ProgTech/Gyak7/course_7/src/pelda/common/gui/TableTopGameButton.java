package pelda.common.gui;

import javax.swing.*;

public class TableTopGameButton extends JButton {
    private final int row;
    private final int column;

    public TableTopGameButton(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }

}
