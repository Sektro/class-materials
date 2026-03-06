package pelda;

import pelda.gui.GameFrame;
import pelda.logic.GameLogic;

import javax.swing.*;

public class Main {

    private static final String NIMBUS = "Nimbus";

    public static void main(String[] args) {
        applyNimbus();
        SwingUtilities.invokeLater(() -> {
            new GameFrame(new GameLogic()).setVisible(true);

        } );
    }

    private static void applyNimbus() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (NIMBUS.equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Failed to use 'Nimbus' look and feel " + e.getMessage());
        }
    }
}
