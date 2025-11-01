package jtree.model;

import lombok.Getter;
import repository.graff_components.GraffNode;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
@Getter
public class GraffTreeItem extends DefaultMutableTreeNode {
    private GraffNode grafNode;

    public GraffTreeItem(GraffNode grafNode) {
        this.grafNode = grafNode;

    }

    @Override
    public String toString() { return grafNode.getTitle(); }

    public void setTitle(String title){
        this.grafNode.setTitle(title);
    }
}
