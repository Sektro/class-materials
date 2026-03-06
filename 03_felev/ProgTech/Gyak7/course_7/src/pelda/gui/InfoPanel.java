package pelda.gui;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    private final JLabel stepCounterLabel;

    public InfoPanel() {
        setPreferredSize(new Dimension(100,50));
        Font textFont = new Font("Calibri",Font.ITALIC, 16);

        JLabel stepCounterTextLabel = createJLabel("Steps: ", textFont);
        stepCounterLabel = createJLabel("", textFont);

        add(stepCounterTextLabel);
        add(stepCounterLabel);

        newGame();
    }

    private JLabel createJLabel(String text, Font font) {
        final JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }

    public final void newGame() { // final ==> nem lehet overrideolni
        setSteps(0);
    }

    public void setSteps(int steps) {
        stepCounterLabel.setText(String.valueOf(steps));
    }
}
