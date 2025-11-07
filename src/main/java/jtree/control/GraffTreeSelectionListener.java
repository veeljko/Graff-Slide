package jtree.control;

import jtree.model.GraffTreeItem;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class GraffTreeSelectionListener implements TreeSelectionListener {
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        GraffTreeItem treeItem = (GraffTreeItem) path.getLastPathComponent();
        System.out.println("Selektovan cvor: " + treeItem.getGraffNode().toString());
        System.out.println("Path: " + e.getPath());
    }
}
