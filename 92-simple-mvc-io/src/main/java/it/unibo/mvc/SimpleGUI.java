package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame("My first Java graphical interface");

    private SimpleGUI(final Controller controller) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JTextArea textArea = new JTextArea();
        final JPanel panel = new JPanel();
        final LayoutManager layout = new BorderLayout();
        panel.setLayout(layout);
        final JButton save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.save(textArea.getText());
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(textArea, BorderLayout.CENTER);
        panel.add(save, BorderLayout.SOUTH);
        frame.setContentPane(panel);
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 4, sh / 4);
        frame.setLocationByPlatform(true);
    }
    private void display() {
        frame.setVisible(true);
    }

    /**
     * Launch GUI.
     * 
     * @param args
     */
    public static void main(final String... args) {
        final SimpleGUI gui = new SimpleGUI(new Controller());
        gui.display();
    }

}
