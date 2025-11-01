package raf.graffito.dsw.controller;

import jtree.model.GraffTreeItem;
import raf.graffito.dsw.gui.swing.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RemoveChildAction extends AbstractGraffAction{
    public RemoveChildAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK)); // Ovo je prečica za izlaz
        putValue(SMALL_ICON, super.loadIcon("/images/remove-node-icon.png")); // Postavljanje ikonice
        putValue(NAME, "Remove Folder"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "Remove Folder"); // Tooltip
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GraffTreeItem selected = (GraffTreeItem) MainFrame.getInstance().getTree().getSelectedNode();
        MainFrame.getInstance().getTree().removeNode(selected);
    }
}
