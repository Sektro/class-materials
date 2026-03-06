package Michael_Barnsley;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PafranyGUI {
    private JFrame frame;
    private Pafrany pafrany;
    private DrawArea drawArea;
    private int maximumIterations;

    public PafranyGUI(int maximumIterations) {
        this.maximumIterations = maximumIterations;
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pafrany = new Pafrany();

        drawArea = new DrawArea(pafrany, maximumIterations);
        drawArea.setPreferredSize(new Dimension(400,400));
        frame.getContentPane().add(BorderLayout.CENTER, drawArea);
        JPanel Buttons = new JPanel();
        JButton drawButton = new JButton(":redraw");
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.repaint();
            }
        });
        Buttons.add(drawButton);
        frame.getContentPane().add(BorderLayout.SOUTH, Buttons);

        frame.setSize(500, 500);
        frame.pack();
        frame.setVisible(true);
    }
}
