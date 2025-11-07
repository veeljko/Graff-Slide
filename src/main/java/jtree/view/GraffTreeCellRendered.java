package jtree.view;

import jtree.model.GraffTreeItem;
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

        if (((GraffTreeItem) value).getGraffNode() instanceof Workspace){
            //dodaj sliku za worksapce
        }
        else if (((GraffTreeItem) value).getGraffNode() instanceof Project){
            //dodaj sliku za project
        }
        else if (((GraffTreeItem) value).getGraffNode() instanceof Presentation){
            //dodaj sliku za presentation
        }
        else if (((GraffTreeItem) value).getGraffNode() instanceof Slide){
            //dodaj sliku za Slide
        }

        Icon icon = null;
        if (imageURL != null) icon = new ImageIcon(imageURL);
        setIcon(icon);
        return this;
    }


}
