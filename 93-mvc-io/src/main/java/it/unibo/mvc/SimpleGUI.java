package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final String PRINT = "Print";
    private static final String SHOW_HISTORY = "Show History";
    private final JFrame frame = new JFrame();
    private final Controller controller;
    
    public SimpleGUI(final Controller controller) {
        this.controller=controller;
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JTextField textField = new JTextField();
        final JTextArea textArea = new JTextArea(PRINT);
        canvas.add(textField, BorderLayout.NORTH);
        textArea.setEditable(false);
        canvas.add(textArea, BorderLayout.CENTER);
        final JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.LINE_AXIS));
        canvas.add(southPanel, BorderLayout.SOUTH);
        final JButton print = new JButton(PRINT);
        final JButton history = new JButton(SHOW_HISTORY);
        southPanel.add(print);
        southPanel.add(history);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                SimpleGUI.this.controller.setNextStringToPrint(textField.getText());
                SimpleGUI.this.controller.printCurrentString();
            }           
        });
        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                final StringBuilder builder = new StringBuilder();
                final List<String> history = SimpleGUI.this.controller.getHistoryPrintedStrings();
                for (final String string : history) {
                    builder.append(string).append("\n");
                }
                if (!history.isEmpty()) {
                    builder.deleteCharAt(builder.length() - 1);
                }
                textArea.setText(builder.toString());
            }
        });
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setLocationByPlatform(true);
    }

    private void display() {
        frame.setVisible(true);
    }

    public static void main(final String... args) {
        final SimpleGUI gui = new SimpleGUI(new SimpleController());
        gui.display();
    }
}
