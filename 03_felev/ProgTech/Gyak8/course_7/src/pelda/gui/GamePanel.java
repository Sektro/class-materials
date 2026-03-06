package pelda.gui;

import pelda.common.gui.TableTopGameButton;
import pelda.logic.GameLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {

    private final GameLogic logic;
    //TODO: saját actionListener
    private final ActionListener buttonActionListener = new ButtonActionListener();
    private final InfoPanel infoPanel;

    public GamePanel(GameLogic logic, InfoPanel infoPanel) {
        this.logic = logic;
        this.infoPanel = infoPanel;
        newGame();
    }

    public void newGame() {
        setupGamePanel();
        refreshUI();
    }

    private void setupGamePanel() {
        removeAll();
        int size = logic.getSize();
        setLayout(new GridLayout(size, size));
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                JButton btn = new TableTopGameButton(i, j);
                btn.setPreferredSize(new Dimension(40, 40));
                //TODO: actionListener
                btn.addActionListener(buttonActionListener);
                add(btn);
            }
        }
        revalidate();
        repaint();
    }

    private void refreshUI() {
        for (Component component : getComponents()) {
            TableTopGameButton button = (TableTopGameButton) component;
            int fieldValue = logic.getFieldsValue(button.getRow(), button.getColumn());
            button.setBackground(getColorByValue(fieldValue));
        }
    }

    private Color getColorByValue(int fieldValue) {
        return switch (fieldValue) {
          case 0 -> Color.decode("#eccf7e");
          case 1 -> Color.decode("#ffad8d");
          case 2 -> Color.decode("#ff96b7");
            default -> Color.decode("#d394df");
        };
    }

    private class ButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            TableTopGameButton button = (TableTopGameButton) e.getSource();
            logic.changeFieldValues(button.getRow(), button.getColumn());
            infoPanel.setSteps(logic.getSteps());
            refreshUI();
        }

        private void checkForGameEnding() {
            if (logic.isGameEnd()) {
                JOptionPane.showMessageDialog(null, "Congratulations! You won!", "Color Game winner", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

}
