package jtree.view;

import jtree.control.GraffTreeCellEditor;
import jtree.control.GraffTreeMouseListener;
import jtree.control.GraffTreeDragDropHandler;
import jtree.control.GraffTreeSelectionListener;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import jtree.control.GraffTreeDragDropHandler;

public class GraffTreeView extends JTree {

    public GraffTreeView(DefaultTreeModel model) {
        setModel(model);
        GraffTreeCellRendered renderer = new GraffTreeCellRendered();
        addTreeSelectionListener(new GraffTreeSelectionListener());
        setCellEditor(new GraffTreeCellEditor(this, renderer));
        setCellRenderer(renderer);
        setDragEnabled(true);
        setDropMode(DropMode.ON_OR_INSERT);
        setTransferHandler(new GraffTreeDragDropHandler(this));
        setEditable(true);
        addMouseListener(new GraffTreeMouseListener());
    }
}
