package jtree.control;

import raf.graffito.dsw.gui.swing.MainFrame;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import repository.graff_implementation.Project;
import repository.graff_node_decorator.GraffNodeDecorator;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GraffTreeMouseListener extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && SwingUtilities.isRightMouseButton(e)) {
            GraffNode selected = MainFrame.getInstance().getTree().getSelectedNode().getGraffNode();
            GraffNode unwrap = selected;
            if (unwrap instanceof GraffNodeDecorator) unwrap = ((GraffNodeDecorator) unwrap).getBaseGraffNode();
            //System.out.println(selected);
            if (unwrap instanceof Project) {
               // System.out.println("true");
                MainFrame.getInstance().getTabbedPane().removeAll();
                MainFrame.getInstance().getTabbedPane().addTabs(selected);
            }
        }
    }
}
