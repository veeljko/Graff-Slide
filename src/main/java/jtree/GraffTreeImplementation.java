package jtree;

import jtree.nodechangeobserver.INodeChangePublisher;
import jtree.nodechangeobserver.INodeChangeSubscriber;
import jtree.nodechangeobserver.NotificationType;
import jtree.panels.ColorChoserPanel;
import jtree.panels.ConfirmPanel;
import jtree.model.GraffTreeItem;
import jtree.view.GraffTreeView;
import repository.graff_components.GraffLeaf;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import repository.graff_components.GraffNodeType;
import repository.graff_implementation.Presentation;
import repository.graff_implementation.Project;
import repository.graff_implementation.Slide;
import repository.graff_implementation.Workspace;
import repository.graff_node_decorator.GraffNodeColorDecorator;
import repository.graff_node_decorator.GraffNodeDecorator;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import error_handler.ErrorMessage;
import error_handler.ErrorType;
import jtree.panels.ConfirmPanel;
import jtree.model.GraffTreeItem;
import jtree.view.GraffTreeView;
import raf.graffito.dsw.core.ApplicationFramework;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import repository.graff_implementation.*;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.time.LocalDateTime;
import java.util.Random;

public class GraffTreeImplementation implements GraffTree, INodeChangePublisher {
    private GraffTreeView graffTreeView;
    private DefaultTreeModel treeModel;
    private List<INodeChangeSubscriber> subs = new ArrayList<>();
    private GraffRepositoryFactory graffFactory = new GraffRepositoryFactory();

    @Override
    public GraffTreeView generateTree(Workspace workspace) {
        GraffTreeItem root = new GraffTreeItem(workspace);
        treeModel = new DefaultTreeModel(root);
        graffTreeView = new GraffTreeView(treeModel);
        return graffTreeView;
    }

    @Override
    public void addChild(GraffTreeItem parent) {
        if (((parent.getGraffNode()) instanceof GraffLeaf)) return;
        if(parent == null){
            ErrorMessage erMsg = new ErrorMessage("Morate izabrati čvor", ErrorType.ERROR, LocalDateTime.now());
            ApplicationFramework.getInstance().getMsgGen().notifyAll(erMsg);
            return;
        }
        if (!((parent.getGrafNode()) instanceof GraffNodeComposite)) return;

        GraffNode child = createChild(parent.getGraffNode());
        if (parent.getGraffNode().getType() == GraffNodeType.WORKSPACE) {
            ColorChoserPanel colorChoserPanel = new ColorChoserPanel();
            Color color = new Color(
                    Integer.parseInt(colorChoserPanel.getRField().getText()),
                    Integer.parseInt(colorChoserPanel.getGField().getText()),
                    Integer.parseInt(colorChoserPanel.getBField().getText())
            );

            child = new GraffNodeColorDecorator(child, color);
        }
        updateAll(child, NotificationType.ADD);
        parent.add(new GraffTreeItem(child));

        ((GraffNodeComposite) parent.getGraffNode()).addChild(child);
        graffTreeView.expandPath(graffTreeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(graffTreeView);
    }

    @Override
    public void removeNode(GraffTreeItem node) {
        if (node.getGraffNode().getType() == GraffNodeType.WORKSPACE) return;
        if (node.getGrafNode() instanceof Workspace){
            ErrorMessage erMsg = new ErrorMessage("Ne možete izbrisati Workspace", ErrorType.ERROR, LocalDateTime.now());
            ApplicationFramework.getInstance().getMsgGen().notifyAll(erMsg);
            return;
        }
        GraffTreeItem parent = (GraffTreeItem) node.getParent();

        updateAll(node.getGraffNode(), NotificationType.DELETE);
        parent.remove(node);
        ((GraffNodeComposite)parent.getGraffNode()).removeChild(node.getGraffNode());
        graffTreeView.expandPath(graffTreeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(graffTreeView);
    }

    @Override
    public void editNode(GraffTreeItem target, String title, String author) {
        target.editNode(title, author);
        updateAll(target.getGraffNode(), NotificationType.EDIT);
        graffTreeView.expandPath(graffTreeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(graffTreeView);
    }

    @Override
    public GraffTreeItem getSelectedNode() {
        return (GraffTreeItem) graffTreeView.getLastSelectedPathComponent();
    }
    @Override
    public GraffNode createChild(GraffNode parent) {
        if (parent.getType() == GraffNodeType.WORKSPACE) {
            return new Project("project" + new Random().nextInt(100), "", parent);
        }
        if (parent.getType() == GraffNodeType.PROJECT) {
            ConfirmPanel panel = new ConfirmPanel();
            if (panel.getOpcija1().isSelected()){
                return graffFactory.createSlide("slide" + new Random().nextInt(100), "", parent);
            }else {
                return graffFactory.createPresentation("presentation" + new Random().nextInt(100), "", parent);
            }
        }
        if (parent.getType() == GraffNodeType.PRESENTATION) {
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
