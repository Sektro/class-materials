package pelda;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author bli
 */
public class CalcGUI {

    private JFrame frame;
    private JPanel numPanel;
    private JPanel buttonPanel;
    private JTextField operand1;
    private JTextField operand2;
    private JTextField result;

    public CalcGUI(int fieldWidth) {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        numPanel = new JPanel();
        numPanel.setLayout(new BoxLayout(numPanel, BoxLayout.Y_AXIS));
        operand1 = new JTextField(fieldWidth);
        numPanel.add(operand1);
        operand2 = new JTextField(fieldWidth);
        numPanel.add(operand2);
        result = new JTextField(fieldWidth);
        numPanel.add(result);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1));

        String[] operations = { "+", "-", "*", "/", "^", "v-" };
        for (String operation : operations){
            JButton button = new JButton(operation);
            button.addActionListener(calculationListener);
            buttonPanel.add(button);
        }

        frame.getContentPane().add(BorderLayout.WEST, numPanel);
        frame.getContentPane().add(BorderLayout.EAST, buttonPanel);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu calcMenu = new JMenu("Calc");
        menuBar.add(calcMenu);
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        calcMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

        frame.pack();
        frame.setVisible(true);
    }

    public void calculate(String operation) {
        try {
            double op1 = Double.valueOf(operand1.getText());
            double op2 = Double.valueOf(operand2.getText());
            double res = 0;
            switch (operation) {
                case "+":
                    res = op1 + op2;
                    break;
                case "-":
                    res = op1 - op2;
                    break;
                case "*":
                    res = op1 * op2;
                    break;
                case "/":
                    res = op1 / op2;
                    break;
                case "^":
                    res = Math.pow(op1, op2);
                    break;
                case "v-":
                    res = Math.sqrt(op1);
                    break;
            }
            result.setText(String.valueOf(res));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Wrong number format!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    ActionListener calculationListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof JButton){
                JButton btn = (JButton)e.getSource();
                String operation = btn.getText();
                calculate(operation);
            }
        }
    };

    class CalcActionListener implements ActionListener {

        private final String operation;

        public CalcActionListener(String operation) {
            this.operation = operation;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            calculate(operation);
        }

    }
}