package Michael_Barnsley;

import javax.swing.*;
import java.awt.*;

public class DrawArea extends JPanel {
    private Pafrany pafrany;
    private Color background = Color.WHITE;
    private int maximumIterations;

    public  DrawArea(Pafrany pafrany, int maximumIterations) {
        this.pafrany = pafrany;
        this.maximumIterations = maximumIterations;
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        Graphics2D g = (Graphics2D) grphcs;
        setBackground(background);
        pafrany.drawPafrany(maximumIterations,g);
    }
}
