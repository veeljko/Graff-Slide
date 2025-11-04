package jtree;

import error_handler.observer.Subscriber;
import jtree.nodechangeobserver.INodeChangePublisher;
import jtree.nodechangeobserver.INodeChangeSubscriber;
import jtree.nodechangeobserver.NotificationType;
import jtree.panels.ConfirmPanel;
import jtree.model.GraffTreeItem;
import jtree.view.GraffTreeView;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import repository.graff_implementation.Presentation;
import repository.graff_implementation.Project;
import repository.graff_implementation.Slide;
import repository.graff_implementation.Workspace;
import repository.graff_node_decorator.GraffNodeColorDecorator;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraffTreeImplementation implements GraffTree, INodeChangePublisher {
    private GraffTreeView graffTreeView;
    private DefaultTreeModel treeModel;
    private List<INodeChangeSubscriber> subs = new ArrayList<>();

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
        updateAll(child, NotificationType.ADD);
        parent.add(new GraffTreeItem(child));

        ((GraffNodeComposite)parent.getGrafNode()).addChild(child);
        graffTreeView.expandPath(graffTreeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(graffTreeView);
    }

    @Override
    public void removeNode(GraffTreeItem node) {
        if (node.getGrafNode() instanceof Workspace) return;
        GraffTreeItem parent = (GraffTreeItem) node.getParent();
        updateAll(node.getGrafNode(), NotificationType.DELETE);
        parent.remove(node);
        ((GraffNodeComposite) parent.getGrafNode()).removeChild(node.getGrafNode());
        graffTreeView.expandPath(graffTreeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(graffTreeView);
    }

    @Override
    public void editNode(GraffTreeItem target, String title, String author) {
        target.editNode(title, author);
        updateAll(target.getGrafNode(), NotificationType.EDIT);
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

    @Override
    public void updateAll(Object notification, NotificationType type) {
        for (INodeChangeSubscriber sub : subs) sub.update(notification, type);
    }

    @Override
    public void addSubscriber(INodeChangeSubscriber sub) {
        if (subs.contains(sub)) return;
        subs.add((INodeChangeSubscriber) sub);
    }

    @Override
    public void removeSubscriber(INodeChangeSubscriber sub) {
        if (subs.contains(sub)) subs.remove(sub);
    }
}
