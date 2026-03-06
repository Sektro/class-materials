package Michael_Barnsley;

import java.awt.*;
import java.util.Random;

public class Pafrany {

    private int x = 0;
    private int y = 0;
    private int t = 0;
    private int xn = 0;
    private int yn = 0;


    public Pafrany() {
    }

    public void drawPafrany(int maximumIterations, Graphics2D g) {
        double r;
        g.setColor(Color.BLACK);
        while (t < maximumIterations) {
            r = (double)Math.random();
            if (r < 0.01) {
                xn = 0;
                yn = 16 * y;
            }
            else if (r < 0.86) {
                xn = 85 * x + 4 * y;
                yn = -4 * x + 85 * y + 160;
            }
            else if (r < 0.93) {
                xn = 2 * x - 26 * y;
                yn = 23 * x + 22 * y + 6;
            }
            else {
                xn = -15 * x + 28 * y;
                yn = 26 * x + 24 * y + 44;
            }
            g.drawOval((int)xn, (int)yn, 1, 1);
            x = xn;
            y = yn;
            t++;
        }
    }
}
