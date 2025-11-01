package jtree;

import jtree.panels.ConfirmPanel;
import jtree.model.GraffTreeItem;
import jtree.view.GraffTreeView;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import repository.graff_implementation.Presentation;
import repository.graff_implementation.Project;
import repository.graff_implementation.Slide;
import repository.graff_implementation.Workspace;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.util.Random;

public class GraffTreeImplementation implements GraffTree{
    private GraffTreeView graffTreeView;
    private DefaultTreeModel treeModel;

    @Override
    public GraffTreeView generateTree(Workspace workspace) {
        GraffTreeItem root = new GraffTreeItem(workspace);
        treeModel = new DefaultTreeModel(root);
        graffTreeView = new GraffTreeView(treeModel);
        return graffTreeView;
    }

    @Override
    public void addChild(GraffTreeItem parent) {
        if (!((parent.getGrafNode()) instanceof GraffNodeComposite)) return;

        GraffNode child = createChild(parent.getGrafNode());
        parent.add(new GraffTreeItem(child));
        ((GraffNodeComposite)parent.getGrafNode()).addChild(child);
        graffTreeView.expandPath(graffTreeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(graffTreeView);
    }

    @Override
    public void removeNode(GraffTreeItem node) {
        if (node.getGrafNode() instanceof Workspace) return;
        GraffTreeItem parent = (GraffTreeItem) node.getParent();
        parent.remove(node);
        ((GraffNodeComposite) parent.getGrafNode()).removeChild(node.getGrafNode());
        graffTreeView.expandPath(graffTreeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(graffTreeView);
    }

    @Override
    public void editNode(GraffTreeItem target, String title, String author) {
        target.editNode(title, author);
        graffTreeView.expandPath(graffTreeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(graffTreeView);
    }

    @Override
    public GraffTreeItem getSelectedNode() {
        return (GraffTreeItem) graffTreeView.getLastSelectedPathComponent();
    }
    @Override
    public GraffNode createChild(GraffNode parent) {
        if (parent instanceof Workspace) {
            return new Project("project" + new Random().nextInt(100), "", parent);
        }
        if (parent instanceof Project) {
            ConfirmPanel panel = new ConfirmPanel();
            if (panel.getOpcija1().isSelected()) return new Slide("slide" + new Random().nextInt(100), "", parent);
            return new Presentation("presentation" + new Random().nextInt(100), "", parent);

        }
        if (parent instanceof Presentation) {
            return new Slide("slide" + new Random().nextInt(100), "", parent);
        }

        return null;

    }
}
