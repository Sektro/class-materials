package onallo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KeyboardGUI {

    private JFrame frame;
    private JPanel TextPanel;
    private JPanel buttonPanel;
    private JTextField text;

    public KeyboardGUI(int fieldWidth) {
        frame = new JFrame("QWERTY");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TextPanel = new JPanel();
        TextPanel.setLayout(new BoxLayout(TextPanel, BoxLayout.X_AXIS));
        text = new JTextField(fieldWidth);
        TextPanel.add(text);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 8));

        String[] operations = { "Q", "W", "E", "R", "T", "Y", "Backspace", "CLR" };
        for (String operation : operations){
            JButton button = new JButton(operation);
            button.addActionListener(typeListener);
            buttonPanel.add(button);
        }

        frame.getContentPane().add(BorderLayout.SOUTH, TextPanel);
        frame.getContentPane().add(BorderLayout.NORTH, buttonPanel);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu textMenu = new JMenu("QWERTY");
        menuBar.add(textMenu);
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        textMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

        frame.pack();
        frame.setVisible(true);
    }

    public void typing(String operation) {
        String textSoFar = String.valueOf(text.getText());
        switch (operation) {
            case "Q":
                text.setText(textSoFar + "Q");
                break;
            case "W" :
                text.setText(textSoFar + "W");
                break;
            case "E" :
                text.setText(textSoFar + "E");
                break;
            case "R" :
                text.setText(textSoFar + "R");
                break;
            case "T" :
                text.setText(textSoFar + "T");
                break;
            case "Y" :
                text.setText(textSoFar + "Y");
                break;
            case "Backspace" :
                /*
                String replacement = "";
                if (textSoFar.length() > 1) {
                    char[] converted = textSoFar.toCharArray();
                    for (int i = 0; i < converted.length - 1; i++) {
                        replacement = replacement + converted[i];
                    }
                }
                text.setText(textSoFar);
                asd
                 */
                if (!textSoFar.isEmpty()) {
                    text.setText(textSoFar.substring(0,textSoFar.length() - 1));
                }
                else {
                    text.setText("");
                }
                break;
            case "CLR" :
                text.setText("");
                break;

        }
    }

    ActionListener typeListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof JButton){
                JButton btn = (JButton)e.getSource();
                String operation = btn.getText();
                typing(operation);
            }
        }
    };
}
