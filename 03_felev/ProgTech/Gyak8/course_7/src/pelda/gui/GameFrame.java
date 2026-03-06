package pelda.gui;

import pelda.logic.GameLogic;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameFrame extends JFrame {
    private final GameLogic gameLogic;
    private final GamePanel gamePanel;
    private final InfoPanel infoPanel;

    public GameFrame(GameLogic logic) {
        this.gameLogic = logic;
        setFrameProperties();
        this.gameLogic.newGame(10);

        this.infoPanel = new InfoPanel();
        getContentPane().add(infoPanel, BorderLayout.NORTH);
        this.gamePanel = new GamePanel(logic, infoPanel);
        getContentPane().add(gamePanel, BorderLayout.SOUTH);
        setJMenuBar(new GameMenuBar());

        pack();
    }

    private void setFrameProperties() {
        setTitle("Color Game - 7.gyak");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(100,100);
    }

    private class GameMenuBar extends JMenuBar {
        private final Action newGameAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final Integer[] gameSize = new Integer[]{5, 10, 15, 20};
                final Object resultObject = JOptionPane.showInputDialog(rootPane, "Select a table size", "New Game", JOptionPane.QUESTION_MESSAGE, null, gameSize, gameSize[1]); //rootPane ==> középen
                if (resultObject != null) {
                    int userGameSize = (int) resultObject;
                    gameLogic.newGame((userGameSize));
                    gamePanel.newGame();
                    infoPanel.newGame();
                    pack();
                }
            }
        };


        public GameMenuBar() {
            JMenu gameMenu = new JMenu("Game");
            JMenuItem newGameItem = new JMenuItem(newGameAction);
            newGameItem.setText("New Game");
            newGameItem.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
            gameMenu.add(newGameItem);
            add(gameMenu);
        }
    }
}
