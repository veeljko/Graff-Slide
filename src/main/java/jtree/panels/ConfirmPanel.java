package jtree.panels;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
@Getter
public class ConfirmPanel extends JPanel {
    private JRadioButton opcija1;
    private JRadioButton opcija2;

    public ConfirmPanel(){
        initialize();
    }

    private void initialize(){
        opcija1 = new JRadioButton("SLIDE");
        opcija2 = new JRadioButton("PRESENTATION");
        ButtonGroup group = new ButtonGroup();
        group.add(opcija1);
        group.add(opcija2);
        opcija1.setSelected(true);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(new Label("Izaberite: "));
        this.add(opcija1);
        this.add(opcija2);

        JOptionPane.showConfirmDialog(null, this, "Izbor opcije", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    }
}
