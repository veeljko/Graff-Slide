package jtree.control;

import com.sun.tools.javac.Main;
import jtree.model.GraffTreeItem;
import raf.graffito.dsw.gui.swing.MainFrame;
import repository.graff_components.GraffNode;
import repository.graff_implementation.Project;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GraffTreeMouseListener extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && SwingUtilities.isRightMouseButton(e)) {
            GraffNode selected = MainFrame.getInstance().getTree().getSelectedNode().getGrafNode();
            if (selected instanceof Project) {
                MainFrame.getInstance().getTabbedPane().removeAll();
                MainFrame.getInstance().getTabbedPane().addTabs(selected);
            }
        }
    }
}
