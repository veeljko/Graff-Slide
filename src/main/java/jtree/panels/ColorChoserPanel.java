package jtree.panels;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class ColorChoserPanel extends JPanel {
    private JTextField rField;
    private JTextField gField;
    private JTextField bField;

    public ColorChoserPanel() {
        initialize();
    }

    private void initialize() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Unesite RGB vrednosti (0-255):");
        this.add(title);

        JPanel rPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rPanel.add(new JLabel("R:"));
        rField = new JTextField(3);
        rPanel.add(rField);
        this.add(rPanel);

        JPanel gPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        gPanel.add(new JLabel("G:"));
        gField = new JTextField(3);
        gPanel.add(gField);
        this.add(gPanel);

        JPanel bPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bPanel.add(new JLabel("B:"));
        bField = new JTextField(3);
        bPanel.add(bField);
        this.add(bPanel);

        // Prikaz dijaloga odmah pri kreiranju panela
        int result = JOptionPane.showConfirmDialog(
                null,
                this,
                "Unos RGB boje",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            try {
                int r = Integer.parseInt(rField.getText());
                int g = Integer.parseInt(gField.getText());
                int b = Integer.parseInt(bField.getText());

                if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
                    JOptionPane.showMessageDialog(null, "Vrednosti moraju biti između 0 i 255!");
                } else {
                    Color color = new Color(r, g, b);
                    // Na primer: boja može biti upotrebljena odmah
                    this.setBackground(color);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Unesite validne brojeve!");
            }
        }
    }

}
