package view;

import model.Difficulty;
import model.GameConstants;
import model.GameStates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameFrame extends JFrame {
    private GameStates gameState;
    private GamePanel gamePanel;
    private PausedMenuPanel pausedMenuPanel;
    private MainMenuPanel mainMenuPanel;
    private ShopPanel shopPanel;
    private TimePanel timePanel;
    private Difficulty difficulty = Difficulty.MEDIUM;

    public GameFrame() throws IOException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setPreferredSize(new Dimension(GameConstants.CAMERA_WIDTH + GameConstants.SHOP_PANEL_WIDTH + 500, GameConstants.CAMERA_HEIGHT + 100));
        this.setTitle("Safari");

        mainMenuPanel = new MainMenuPanel();
        gamePanel = new GamePanel();
        pausedMenuPanel = new PausedMenuPanel();
        shopPanel = new ShopPanel(gamePanel);
        timePanel = new TimePanel(gamePanel);
        gamePanel.setShopPanel(shopPanel);
        switchGameState(GameStates.MENU);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void switchGameState(GameStates newGameState) {
        gameState = newGameState;
        switch (gameState) {
            case MENU -> {
                this.getContentPane().removeAll();
                gamePanel.stopTimer();
                this.setContentPane(mainMenuPanel);
                mainMenuPanel.initiate();
                this.revalidate();
                this.repaint();
            }
            case PAUSED -> {
                this.getContentPane().removeAll();
                gamePanel.togglePause("PAUSE");
                timePanel.updateTimeButtons();
                gamePanel.stopTimer();
                this.setContentPane(pausedMenuPanel);
                pausedMenuPanel.initiate();
                this.revalidate();
                this.repaint();
            }
            case RUNNING -> {
                this.getContentPane().removeAll();

                JPanel topPanel = new JPanel(new BorderLayout());
                JPanel centerPanel = new JPanel(new BorderLayout());

                JButton pauseButton = new JButton("Pause Game");
                pauseButton.addActionListener(e -> switchGameState(GameStates.PAUSED));
                JPanel buttonsPanel = new JPanel();
                buttonsPanel.add(pauseButton);

                topPanel.add(BorderLayout.WEST, buttonsPanel);
                topPanel.add(BorderLayout.CENTER, timePanel);
                centerPanel.add(BorderLayout.CENTER, gamePanel);
                centerPanel.add(BorderLayout.EAST, shopPanel);
                this.getContentPane().add(BorderLayout.NORTH, topPanel);
                this.getContentPane().add(BorderLayout.CENTER, centerPanel);
                this.pack();

                gamePanel.togglePause("RUNNING");

                gamePanel.startTimer();
                gamePanel.requestFocus();
                this.revalidate();
                this.repaint();
            }
        }
    }
    private String changeDifficulty() {
        switch (difficulty) {
            case EASY -> {
                difficulty = Difficulty.MEDIUM;
                return "Medium";
            }
            case MEDIUM -> {
                difficulty = Difficulty.HARD;
                return "Hard";
            }
            case HARD -> {
                difficulty = Difficulty.EASY;
                return "Easy";
            }
        }
        return "";
    }

    private class PausedMenuPanel extends JPanel {
        private JButton mainMenuButton;
        private JButton continueGameButton;

        private PausedMenuPanel() {
            this.setPreferredSize(new Dimension(GameConstants.CAMERA_WIDTH, GameConstants.CAMERA_HEIGHT));
            this.setBackground(Color.black);
            this.setDoubleBuffered(true);
            this.setFocusable(true);

            mainMenuButton = new JButton("Quit to Main Menu");
            mainMenuButton.addActionListener(e -> switchGameState(GameStates.MENU));

            continueGameButton = new JButton("Continue Game");
            continueGameButton.addActionListener(e -> switchGameState(GameStates.RUNNING));
        }

        private void initiate() {
            this.add(mainMenuButton);
            this.add(continueGameButton);
        }
    }

    private class MainMenuPanel extends JPanel {
        private JButton newGameButton;
        private JButton continueGameButton;
        private JButton changeDifficultyButton;

        private MainMenuPanel() {
            this.setPreferredSize(new Dimension(GameConstants.CAMERA_WIDTH, GameConstants.CAMERA_HEIGHT));
            this.setBackground(Color.black);
            this.setDoubleBuffered(true);
            this.setFocusable(true);

            continueGameButton = new JButton("Continue Game");
            continueGameButton.addActionListener(e -> switchGameState(GameStates.RUNNING));
            newGameButton = new JButton("New Game");
            newGameButton.addActionListener(e -> switchGameState(GameStates.RUNNING));
            changeDifficultyButton = new JButton("Difficulty: Medium");
            changeDifficultyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    changeDifficultyButton.setText("Difficulty: " + changeDifficulty());
                }
            });
        }

        private void initiate() {
            this.add(continueGameButton);
            this.add(newGameButton);
            this.add(changeDifficultyButton);
        }
    }
}
