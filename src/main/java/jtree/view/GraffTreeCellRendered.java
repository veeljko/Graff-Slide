package jtree.view;

import jtree.model.GraffTreeItem;
import repository.graff_components.GraffNodeType;
import repository.graff_implementation.Presentation;
import repository.graff_implementation.Project;
import repository.graff_implementation.Slide;
import repository.graff_implementation.Workspace;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class GraffTreeCellRendered extends DefaultTreeCellRenderer {
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        URL imageURL = null;

        if (((GraffTreeItem) value).getGraffNode().getType() == GraffNodeType.WORKSPACE){
            imageURL = getClass().getResource("/images/workspace.png");
        }
        else if (((GraffTreeItem) value).getGraffNode().getType() == GraffNodeType.PROJECT){
            imageURL = getClass().getResource("/images/project.png");
        }
        else if (((GraffTreeItem) value).getGraffNode().getType() == GraffNodeType.PRESENTATION){
            imageURL = getClass().getResource("/images/presentation.png");
        }
        else if (((GraffTreeItem) value).getGraffNode().getType() == GraffNodeType.SLIDE){
            imageURL = getClass().getResource("/images/slide.png");
        }

        Icon icon = null;
        if (imageURL != null) icon = new ImageIcon(imageURL);

        setIcon(icon);
        return this;
    }


}
