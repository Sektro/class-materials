package hu.elte.progtech.board;

import hu.elte.progtech.logic.Clock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RubikGUI {
    private JFrame frame;
    private JTextField[][] clocks;
    private JButton[][] buttons;
    private Clock[] clockValues;
    private JPanel clockPanel;
    private Random random = new Random();
    private int clickNum = 0;
    private JButton newGame;
    private Container resetter;

    public RubikGUI() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startNewGame();
        frame.pack();
        frame.setVisible(true);

        resetter = frame.getContentPane();
    }

    //új játék kezdése, újraépíti a frame-t (használva van a konstruktorban és a resetGame() függvényben)
    private void startNewGame() {
        clockPanel = new JPanel(new GridLayout(5,5));
        clocks = new JTextField[5][5];
        clockValues = new Clock[9];
        buttons = new JButton[2][2];
        int clockCounter = 0;
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                if (i == 0 || i == 2 || i == 4) {
                    if (j == 0 || j == 2 || j == 4) {
                        clockValues[clockCounter] = new Clock(clockCounter+1);
                        JTextField textField = new JTextField(10);
                        textField.setBackground(Color.white);
                        textField.setPreferredSize(new Dimension(200, 200));
                        textField.setText(String.valueOf(clockValues[clockCounter].getTime()) + ":00");
                        Font bigFont = textField.getFont().deriveFont(Font.PLAIN, 40f);
                        textField.setFont(bigFont);
                        clocks[i][j] = textField;
                        clockPanel.add(textField);
                        ++clockCounter;
                    }
                    else {
                        JTextField textField = new JTextField(1);
                        textField.setBackground(Color.black);
                        clocks[i][j] = textField;
                        clockPanel.add(textField);
                    }
                }
                else {
                    if (j == 1 || j == 3) {
                        JButton button = new JButton();
                        button.addActionListener(new ButtonListener(i, j));
                        button.setPreferredSize(new Dimension(10, 10));
                        button.setBackground(Color.RED);
                        int x = 0;
                        int y = 0;
                        if (i == 3) {x = 1;}
                        if (j == 3) {y = 1;}
                        buttons[x][y] = button;
                        clockPanel.add(button);
                    }
                    else {
                        JTextField textField = new JTextField(1);
                        textField.setBackground(Color.black);
                        clocks[i][j] = textField;
                        clockPanel.add(textField);
                    }
                }
            }
        }
        newGame = new JButton();
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*for (Clock c : clockValues) {c.setTime(12);}
                checkForGameOver();
                tesztelésre!!!
                 */
                resetGame();
            }
        });
        newGame.setPreferredSize(new Dimension(80,40));
        newGame.setText("Start New Game");

        frame.getContentPane().add(BorderLayout.CENTER, clockPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, newGame);


        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu rubikMenu = new JMenu("Rubik's clock");
        menuBar.add(rubikMenu);
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        rubikMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
    }

    //egy Container JComponent használatával kitörli, majd újraépíti a frame tartalmát (használva van a "start new game" gombban)
    private void resetGame() {
        resetter.removeAll();
        resetter.revalidate();
        resetter.repaint();
        startNewGame();
    }

    //gombnyomáskor meghívott függvény, addig vizsgálja az órákat, amíg vagy talál egyet ami nem helyes (12:00), vagy végig nem ér, mikor is játék végi eseményt triggerel
    private void checkForGameOver() {
        boolean isOver = true;
        int i = 0;
        while (isOver && i < 9) {
            if (clockValues[i].getTime() != 12) {isOver = false;}
            ++i;
        }
        if (isOver) {
            JOptionPane.showMessageDialog(clockPanel, "You have completed the puzzle with " + clickNum + " steps!", "Congrats!",
                    JOptionPane.PLAIN_MESSAGE);
            resetGame();
        }
    }

    //segítő class, kezeli a gombokkal való interakciót
    class ButtonListener implements ActionListener {

        private int[] targets;

        public ButtonListener(int x, int y) {
            if (x == 1 && y == 1) {
                targets = new int[]{1, 2, 4, 5};
            }
            else if (x == 1 && y == 3) {
                targets = new int[]{2, 3, 5, 6};
            }
            else if (x == 3 && y == 1) {
                targets = new int[]{4, 5, 7, 8};
            }
            else if (x == 3 && y == 3) {
                targets = new int[]{5, 6, 8, 9};
            }
            else {
                targets = new int[0];
            }
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            for (int t : targets) {
                boolean found = false;
                int i = 0;
                while (!found) {
                    if (t == clockValues[i].getTargetNum()) {
                        clockValues[i].addTime();
                        found = true;
                        switch (clockValues[i].getTargetNum()) {
                            case 1 :
                                clocks[0][0].setText(String.valueOf(clockValues[i].getTime()) + ":00");
                                break;
                            case 2 :
                                clocks[0][2].setText(String.valueOf(clockValues[i].getTime()) + ":00");
                                break;
                            case 3 :
                                clocks[0][4].setText(String.valueOf(clockValues[i].getTime()) + ":00");
                                break;
                            case 4 :
                                clocks[2][0].setText(String.valueOf(clockValues[i].getTime()) + ":00");
                                break;
                            case 5 :
                                clocks[2][2].setText(String.valueOf(clockValues[i].getTime()) + ":00");
                                break;
                            case 6 :
                                clocks[2][4].setText(String.valueOf(clockValues[i].getTime()) + ":00");
                                break;
                            case 7 :
                                clocks[4][0].setText(String.valueOf(clockValues[i].getTime()) + ":00");
                                break;
                            case 8 :
                                clocks[4][2].setText(String.valueOf(clockValues[i].getTime()) + ":00");
                                break;
                            case 9 :
                                clocks[4][4].setText(String.valueOf(clockValues[i].getTime()) + ":00");
                                break;
                        }
                    }
                    ++i;
                }
            }
            ++clickNum;
            checkForGameOver();
        }
    }
}
