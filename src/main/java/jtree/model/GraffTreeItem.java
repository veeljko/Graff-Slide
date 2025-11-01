package jtree.model;

import lombok.Getter;
import lombok.Setter;
import repository.graff_components.GraffNode;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
@Getter
@Setter
public class GraffTreeItem extends DefaultMutableTreeNode {
    private GraffNode grafNode;

    public GraffTreeItem(GraffNode grafNode) {
        this.grafNode = grafNode;

    }

    @Override
    public String toString() { return grafNode.getTitle(); }

    public void setTitle(String actionCommand) {
        grafNode.setTitle(actionCommand);
    }

    public void editNode(String title, String author){
        grafNode.setTitle(title);
        grafNode.setAuthor(author);
    }
}
