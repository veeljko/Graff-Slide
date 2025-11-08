package jtree;

import jtree.nodechangeobserver.INodeChangePublisher;
import jtree.nodechangeobserver.INodeChangeSubscriber;
import jtree.nodechangeobserver.NotificationType;
import jtree.panels.ColorChoserPanel;
import jtree.panels.ConfirmPanel;
import jtree.model.GraffTreeItem;
import jtree.view.GraffTreeView;
import raf.graffito.dsw.gui.swing.MainFrame;
import repository.graff_components.GraffLeaf;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import repository.graff_components.GraffNodeType;
import repository.graff_implementation.Project;
import repository.graff_implementation.Slide;
import repository.graff_implementation.Workspace;
import repository.graff_node_decorator.GraffNodeColorDecorator;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import error_handler.ErrorMessage;
import error_handler.ErrorType;
import raf.graffito.dsw.core.ApplicationFramework;
import repository.graff_implementation.*;


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
        if(parent == null){
            ErrorMessage erMsg = new ErrorMessage("Morate izabrati čvor", ErrorType.ERROR, LocalDateTime.now());
            ApplicationFramework.getInstance().getMsgGen().notifyAll(erMsg);
            return;
        }
        if (parent.getGraffNode() instanceof GraffLeaf) return;

        GraffNode child = createChild(parent.getGraffNode());
        if (parent.getGraffNode().getType() == GraffNodeType.WORKSPACE) {
            ColorChoserPanel colorChoserPanel = new ColorChoserPanel();
            if(colorChoserPanel.getFlag() == 0){
                return;
            }

            Color color = new Color(
                    Integer.parseInt(colorChoserPanel.getRField().getText()),
                    Integer.parseInt(colorChoserPanel.getGField().getText()),
                    Integer.parseInt(colorChoserPanel.getBField().getText())
            );

            if (MainFrame.getInstance().getTabbedPane().getReservedColors().contains(color)){
                ErrorMessage erMsg = new ErrorMessage("Ta boja se vec koristi!", ErrorType.ERROR, LocalDateTime.now());
                ApplicationFramework.getInstance().getMsgGen().notifyAll(erMsg);
                return;
            }

            child = new GraffNodeColorDecorator(child, color);
        }
        updateAll(child, NotificationType.ADD);
        GraffTreeItem childWrapper = new GraffTreeItem(child);
        parent.add(childWrapper);
        ((GraffNodeComposite) parent.getGraffNode()).addChild(child);

        if (child.getType() == GraffNodeType.PROJECT){
            GraffNode defaultSlide = graffFactory.createSlide("slide" + new Random().nextInt(100), "", child);
            childWrapper.add(new GraffTreeItem(defaultSlide));
            ((GraffNodeComposite) child).addChild(defaultSlide);
        }

        graffTreeView.expandPath(graffTreeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(graffTreeView);
    }

    @Override
    public void removeNode(GraffTreeItem node) {

        GraffTreeItem parent = (GraffTreeItem) node.getParent();
        if(!((GraffNodeComposite)parent.getGraffNode()).removeChild(node.getGraffNode())){
            return;
        }

        updateAll(node.getGraffNode(), NotificationType.DELETE);
        parent.remove(node);
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
            return graffFactory.createProject("project" + new Random().nextInt(100), "", parent);
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
            return graffFactory.createSlide("slide" + new Random().nextInt(100), "", parent);
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
