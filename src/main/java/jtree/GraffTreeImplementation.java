package jtree;

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

public class GraffTreeImplementation implements GraffTree{
    private GraffTreeView graffTreeView;
    private DefaultTreeModel treeModel;
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
        if (!((parent.getGrafNode()) instanceof GraffNodeComposite)) return;

        GraffNode child = createChild(parent.getGrafNode());
        parent.add(new GraffTreeItem(child));
        ((GraffNodeComposite)parent.getGrafNode()).addChild(child);
        graffTreeView.expandPath(graffTreeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(graffTreeView);
    }

    @Override
    public void removeNode(GraffTreeItem node) {
        if (node.getGrafNode() instanceof Workspace){
            ErrorMessage erMsg = new ErrorMessage("Ne možete izbrisati Workspace", ErrorType.ERROR, LocalDateTime.now());
            ApplicationFramework.getInstance().getMsgGen().notifyAll(erMsg);
            return;
        }
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
            return graffFactory.createProject("project" + new Random().nextInt(100), "", parent);
        }
        else if (parent instanceof Project) {
            ConfirmPanel panel = new ConfirmPanel();
            if (panel.getOpcija1().isSelected()){
                return graffFactory.createSlide("slide" + new Random().nextInt(100), "", parent);
            }else {
                return graffFactory.createPresentation("presentation" + new Random().nextInt(100), "", parent);
            }
        }
        else if (parent instanceof Presentation) {
            return graffFactory.createSlide("slide" + new Random().nextInt(100), "", parent);
        }

        return null;

    }
}
