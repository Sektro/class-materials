package pelda.common.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.time.Duration;
import java.time.Instant;

public class ElapsedTimeAction extends AbstractAction {
    private final JLabel label;
    private final Instant startTime;

    public ElapsedTimeAction(JLabel timerLabel) {
        this.label = timerLabel;
        this.startTime = Instant.now();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        this.label.setText(formatDuration(Duration.between(startTime,Instant.now())));
    }

    private String formatDuration(Duration duration) {
        long seconds = duration.getSeconds();
        return String.format("%02d:%02d:%02d", seconds / 3600, (seconds % 3600) / 60, seconds % 60);
    }
}
