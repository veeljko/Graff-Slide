package jtree.panels;

import com.sun.tools.javac.Main;
import jtree.model.GraffTreeItem;
import lombok.Getter;
import raf.graffito.dsw.gui.swing.MainFrame;

import javax.swing.*;

import java.awt.*;

@Getter
public class EditPanelView extends JPanel {
    private JTextField titleField = new JTextField();
    private JTextField authorField = new JTextField();

    public EditPanelView() {
        initialize();
    }

    private void initialize(){
        GraffTreeItem treeItem = MainFrame.getInstance().getTree().getSelectedNode();
        titleField.setText(treeItem.getGrafNode().getTitle());
        authorField.setText(treeItem.getGrafNode().getAuthor());

        setSize(300, 200);

        // Glavni panel
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Label i TextField za Title
        JLabel titleLabel = new JLabel("Title:");
        // Label i TextField za Author
        JLabel authorLabel = new JLabel("Author:");

        // Dodavanje komponenti na panel
        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(authorLabel);
        panel.add(authorField);


        add(panel);

        JOptionPane.showConfirmDialog(null, this, "Izbor opcije", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    }
}
