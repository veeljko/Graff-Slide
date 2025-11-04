package jtree.model;

import lombok.Getter;
import lombok.Setter;
import repository.graff_components.GraffNode;
import repository.graff_node_decorator.GraffNodeDecorator;

import javax.swing.tree.DefaultMutableTreeNode;

@Getter
@Setter
public class GraffTreeItem extends DefaultMutableTreeNode {
    private GraffNode graffNode;

    public GraffTreeItem(GraffNode grafNode) {
        this.graffNode = grafNode;

    }

    @Override
    public String toString() { return graffNode.getTitle(); }

    public void setTitle(String actionCommand) {
        graffNode.setTitle(actionCommand);
    }

    public void editNode(String title, String author){
        graffNode.setTitle(title);
        graffNode.setAuthor(author);
    }

}
