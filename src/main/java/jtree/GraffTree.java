package jtree;

import jtree.model.GraffTreeItem;
import repository.graff_components.GraffNode;
import repository.graff_implementation.Workspace;
import jtree.view.GraffTreeView;

public interface GraffTree {
    GraffTreeView generateTree(Workspace workspace);
    void addChild(GraffTreeItem parent);
    void removeNode(GraffTreeItem node);
    GraffNode createChild(GraffNode parent);
    GraffTreeItem getSelectedNode();
}
