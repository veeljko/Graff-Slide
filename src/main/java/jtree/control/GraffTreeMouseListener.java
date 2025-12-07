package jtree.control;

import raf.graffito.dsw.gui.swing.MainFrame;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeType;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GraffTreeMouseListener extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
            GraffNode selected = MainFrame.getInstance().getTree().getSelectedNode().getGraffNode();
            if (selected.getType() == GraffNodeType.PROJECT) {;
                MainFrame.getInstance().getTabbedPane().removeAll();
                MainFrame.getInstance().getTabbedPane().addTabs(selected);
            }
            else if (selected.getType() == GraffNodeType.SLIDE){
                MainFrame.getInstance().getTabbedPane().setSlideView(selected);
            }
        }
    }
}
