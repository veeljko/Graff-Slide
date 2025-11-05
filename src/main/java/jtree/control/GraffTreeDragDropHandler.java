package jtree.control;

import jtree.GraffTree;
import jtree.model.GraffTreeItem;
import jtree.view.GraffTreeView;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import repository.graff_implementation.Slide;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.datatransfer.*;
import java.io.*;
import java.util.*;

/**
 * TransferHandler koji omogućava drag & drop promenu redosleda Slide čvorova
 * u okviru istog roditelja u JTree-u koji koristi GraffTreeItem čvorove.
 */
public class GraffTreeDragDropHandler extends TransferHandler {

    private final JTree tree;
    private GraffTreeItem draggedItem;
    private GraffTreeItem sourceParent;

    public GraffTreeDragDropHandler(JTree tree) {
        this.tree = tree;
    }

    @Override
    public int getSourceActions(JComponent c) {
        return MOVE;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        TreePath path = tree.getSelectionPath();
        if (path == null) return null;

        Object node = path.getLastPathComponent();
        if (!(node instanceof GraffTreeItem)) return null;

        draggedItem = (GraffTreeItem) node;

        // dozvoli drag samo ako predstavlja Slide
        if (!(draggedItem.getGrafNode() instanceof Slide)) return null;

        sourceParent = (GraffTreeItem) draggedItem.getParent();

        return new StringSelection(draggedItem.toString());
    }

    @Override
    public boolean canImport(TransferSupport support) {
        if (!support.isDrop()) return false;

        JTree.DropLocation dl = (JTree.DropLocation) support.getDropLocation();
        TreePath destPath = dl.getPath();
        if (destPath == null || draggedItem == null) return false;

        Object targetObj = destPath.getLastPathComponent();
        if (!(targetObj instanceof GraffTreeItem)) return false;

        GraffTreeItem targetItem = (GraffTreeItem) targetObj;

        GraffTreeItem targetParent = (GraffTreeItem) targetItem.getParent();
        return targetParent == sourceParent; //dozvoli drop samo ako su oba Slide i imaju istog roditelja

    }

    @Override
    public boolean importData(TransferSupport support) {
        if (!canImport(support)) return false;

        JTree.DropLocation dl = (JTree.DropLocation) support.getDropLocation();
        TreePath destPath = dl.getPath();
        GraffTreeItem targetItem = (GraffTreeItem) destPath.getLastPathComponent();
        GraffTreeItem parentItem = (GraffTreeItem) targetItem.getParent();
        if (parentItem == null) return false;

        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();

        // ukloni draggedItem sa starog mesta
        model.removeNodeFromParent(draggedItem);

        // izračunaj novi index
        int index = parentItem.getIndex(targetItem) + 1;


        // ubaci draggedItem na novo mesto
        model.insertNodeInto(draggedItem, parentItem, index);

        // ažuriraj underlying NodeModel listu dece
        GraffNodeComposite parentModel = (GraffNodeComposite) parentItem.getGrafNode();
        if (parentModel != null) {
            parentModel.getChildren().clear();
            for (int i = 0; i < parentItem.getChildCount(); i++) {
                GraffTreeItem child = (GraffTreeItem) parentItem.getChildAt(i);
                parentModel.getChildren().add(child.getGrafNode());
            }
        }

        tree.expandPath(new TreePath(parentItem.getPath()));
        SwingUtilities.updateComponentTreeUI(tree);
        return true;
    }

}
