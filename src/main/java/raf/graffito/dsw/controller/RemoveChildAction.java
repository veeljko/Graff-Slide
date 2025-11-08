package raf.graffito.dsw.controller;

import error_handler.ErrorMessage;
import error_handler.ErrorType;
import jtree.model.GraffTreeItem;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.gui.swing.MainFrame;
import repository.graff_components.GraffNodeType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;

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
        if (selected == null) {
            ErrorMessage erMsg = new ErrorMessage("Izaberite čvor", ErrorType.ERROR, LocalDateTime.now());
            ApplicationFramework.getInstance().getMsgGen().notifyAll(erMsg);
            return;
        }
        if (selected.getGraffNode().getType() == GraffNodeType.WORKSPACE) {
            ErrorMessage erMsg = new ErrorMessage("Ne možete izbrisati Workspace", ErrorType.ERROR, LocalDateTime.now());
            ApplicationFramework.getInstance().getMsgGen().notifyAll(erMsg);
            return;
        }
        MainFrame.getInstance().getTree().removeNode(selected);
    }
}
