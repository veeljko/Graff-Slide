package raf.graffito.dsw.controller;

import jtree.model.GraffTreeItem;
import jtree.panels.EditPanelView;
import raf.graffito.dsw.gui.swing.MainFrame;

import java.awt.event.ActionEvent;

public class EditNodeAction extends AbstractGraffAction {
    public EditNodeAction() {
        putValue(ACCELERATOR_KEY, null); // Ovo je prečica za izlaz
        putValue(SMALL_ICON, super.loadIcon("/images/edit-node-icon.png")); // Postavljanje ikonice
        putValue(NAME, "Edit Folder"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "Edit Folder"); // Tooltip
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GraffTreeItem selected = (GraffTreeItem) MainFrame.getInstance().getTree().getSelectedNode();
        if (selected == null) return;

        EditPanelView panel = new EditPanelView();
        panel.show();

        MainFrame.getInstance().getTree().editNode(selected, panel.getTitleField().getText(), panel.getAuthorField().getText());
    }
}
