package view;

import controller.TimeManager;
import model.time.*;

import javax.swing.*;
import java.awt.*;

public class TimePanel extends JPanel {
    private final TimeManager timeManager;
    private final GamePanel gamePanel;
    private JLabel timeControlLabel;
    private JLabel timeLabel;
    private JLabel dateLabel;
    private JButton speedButton1;
    private JButton speedButton2;
    private JButton speedButton3;

    public TimePanel(GamePanel gamePanel) {
        this.timeManager = gamePanel.getTimeManager();
        this.gamePanel = gamePanel;

        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        setBackground(new Color(0, 0, 160));

        init();
    }

    private void init() {
      // Time display
      timeLabel = new JLabel(timeManager.getFormattedTime());
      timeLabel.setForeground(Color.WHITE);
      timeLabel.setFont(new Font("Arial", Font.BOLD, 16));
      
      // Date display
      dateLabel = new JLabel(timeManager.getFormattedDate());
      dateLabel.setForeground(Color.WHITE);
      dateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
      
      // Speed control buttons
      
      speedButton1 = new JButton("1×");
      speedButton1.setToolTipText("1 hour per minute");
      speedButton1.addActionListener(e -> {
          timeManager.setSpeed(TimeManager.SPEED_HOUR);
          updateTimeButtons();
      });

      speedButton2 = new JButton("2×");
      speedButton2.setToolTipText("1 day per minute");
      speedButton2.addActionListener(e -> {
          timeManager.setSpeed(TimeManager.SPEED_DAY);
          updateTimeButtons();
      });
    
      speedButton3 = new JButton("3×");
      speedButton3.setToolTipText("1 week per minute");
      speedButton3.addActionListener(e -> {
          timeManager.setSpeed(TimeManager.SPEED_WEEK);
          updateTimeButtons();
      });
      
      // Add components to time panel
      add(timeLabel);
      add(dateLabel);
      add(new JSeparator(JSeparator.VERTICAL));
      add(speedButton1);
      add(speedButton2);
      add(speedButton3);

      // Make first speed button selected by default
      updateTimeButtons();
    
       
      // Listen for time changes
      timeManager.addListener(new TimeListener() {
          @Override
          public void onHourChanged(int newHour) {
              timeLabel.setText(timeManager.getFormattedTime());
              // Update lighting based on time of day
              //updateLighting();
          }
           
          @Override
          public void onDayChanged(int newDay) {
              dateLabel.setText(timeManager.getFormattedDate());
          }
           
          @Override
          public void onMonthChanged(int newMonth) {
              dateLabel.setText(timeManager.getFormattedDate());
              // Check win conditions at end of month
              //checkMonthlyConditions();
          }
           
          @Override
          public void onYearChanged(int newYear) {
              dateLabel.setText(timeManager.getFormattedDate());
          }
       });
    }

    // Update which button is highlighted
    public void updateTimeButtons() {
      speedButton1.setBackground((!timeManager.isPaused() && timeManager.getCurrentSpeed() == TimeManager.SPEED_HOUR) ? new Color(100, 100, 100) : null);
      speedButton2.setBackground((!timeManager.isPaused() && timeManager.getCurrentSpeed() == TimeManager.SPEED_DAY) ? new Color(100, 100, 100) : null);
      speedButton3.setBackground((!timeManager.isPaused() && timeManager.getCurrentSpeed() == TimeManager.SPEED_WEEK) ? new Color(100, 100, 100) : null);
    }

    private void updateLighting() {
        // Implement lighting changes based on time of day
        // For example, change background color or brightness of game elements
    }

    private void checkMonthlyConditions() {
        // Implement win conditions based on the end of the month
    }
}
