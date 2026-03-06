package pelda.gui;

import pelda.common.gui.ElapsedTimeAction;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    private final JLabel stepCounterLabel;
    private final JLabel elapsedTimeLabel;

    private Timer elapsedTimer;

    public InfoPanel() {
        setPreferredSize(new Dimension(100,50));
        Font textFont = new Font("Calibri",Font.ITALIC, 16);

        JLabel stepCounterTextLabel = createJLabel("Steps: ", textFont);
        stepCounterLabel = createJLabel("", textFont);
        JLabel elapsedTextLabel = createJLabel("Time: ", textFont);
        elapsedTimeLabel = createJLabel("", textFont);

        add(stepCounterTextLabel);
        add(stepCounterLabel);
        add(new JLabel("        "));
        add(elapsedTextLabel);
        add(elapsedTimeLabel);

        newGame();
    }

    private JLabel createJLabel(String text, Font font) {
        final JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }

    public final void newGame() { // final ==> nem lehet overrideolni
        setSteps(0);
        if (elapsedTimer != null) {
            elapsedTimer.stop();
        }

        elapsedTimeLabel.setText("00:00:00");
        elapsedTimer = new Timer(1000, new ElapsedTimeAction(elapsedTimeLabel));
        elapsedTimer.start();
    }

    public void setSteps(int steps) {
        stepCounterLabel.setText(String.valueOf(steps));
    }
}
