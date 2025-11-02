package jtree.view;

import jtree.control.GraffTreeCellEditor;
import jtree.control.GraffTreeMouseListener;
import jtree.control.GraffTreeSelectionListener;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class GraffTreeView extends JTree {

    public GraffTreeView(DefaultTreeModel model) {
        setModel(model);
        GraffTreeCellRendered renderer = new GraffTreeCellRendered();
        addTreeSelectionListener(new GraffTreeSelectionListener());
        setCellEditor(new GraffTreeCellEditor(this, renderer));
        setCellRenderer(renderer);
        setEditable(true);
        addMouseListener(new GraffTreeMouseListener());
    }
}
