package tabs.graffpanel;

import jtree.nodechangeobserver.INodeChangeSubscriber;
import jtree.nodechangeobserver.NotificationType;
import lombok.Getter;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import repository.graff_components.GraffNodeType;
import tabs.state.slide.rightbar.SlideController;
import tabs.state.slide.SlideView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//ova klasa bi vljd trebala da bude ProjectView
public class GraffTabbedPane extends JTabbedPane implements INodeChangeSubscriber {
    @Getter
    private List<Color> reservedColors = new ArrayList<>();
    public GraffTabbedPane() {
        super();
    }

    public void addTabs(GraffNode node){
        //node mora da bude project
        for (GraffNode child : ((GraffNodeComposite) node).getChildren()){
            if (child.getType() == GraffNodeType.PRESENTATION){
                GraffPanelController panel = new GraffPanelController(child);
                Color color = node.getColor();
                panel.getView().setColor(color);
                addTab(panel.getView());
            }
        }
    }

    public void setSlideView(GraffNode node){
        for (int i = 0; i < getTabCount(); i++) {
            GraffPanelController panel = ((GraffPanelView) getComponentAt(i)).getGraffPanelController();
            if (panel.getNode().equals(node.getParent())){
                SlideController slideController = new SlideController(node, new SlideView(), panel.getStateManager(), panel.getCommandManager());
                panel.setSlideController(slideController);

                panel.getView().revalidate();
                panel.getView().repaint();
                revalidate();
                repaint();
                break;
            }
        }
    }

    private GraffNode getActiveProject(){
        if (getComponentCount() == 0) return null;
        return (((GraffPanelView) getComponentAt(0)).getGraffPanelController()).getNode().getParent();
    }

    public void addTab(GraffPanelView panel){
        addTab(panel.toString(), panel);
        setBackgroundAt(getComponentCount()-1, panel.getColor());
    }


    @Override
    public void update(Object notification, NotificationType type) {
        GraffNode node = null;
        if (notification instanceof GraffNode) node = (GraffNode) notification;


        if (type == NotificationType.EDIT) {
            for (int i = 0; i < getTabCount(); i++) {
                GraffPanelController panel = ((GraffPanelView) getComponentAt(i)).getGraffPanelController();

                if (panel.getNode().equals(node)) {
                    panel.update(node);
                    setTitleAt(i, node.getTitle());

                    panel.getView().revalidate();
                    panel.getView().repaint();
                    revalidate();
                    repaint();
                }
                else if (panel.getNode().getParent().equals(node)) {
                    panel.update(node.getTitle());
                    revalidate();
                    repaint();
                }
            }
        }
        else if (type == NotificationType.DELETE) {
            if (node == null || getActiveProject() == null) return;
            if (node.getType() == GraffNodeType.PROJECT && reservedColors.contains(node.getColor())) reservedColors.remove(node.getColor());
            if (node.getParent().equals(getActiveProject())) {
                removeTabAt(indexOfTab(node.getTitle()));
            }
            else if (node.equals(getActiveProject())) {
                removeAll();
            }
            revalidate();
            repaint();
        }
        else if (type == NotificationType.ADD) {
            if (node.getType() == GraffNodeType.PROJECT) reservedColors.add(node.getColor());
            if (node.getType() != GraffNodeType.PRESENTATION) return;
            if (node.getParent().equals(getActiveProject())) {
                GraffPanelController panel = new GraffPanelController(node);
                panel.getView().setColor(node.getParent().getColor());
                addTab(panel.getView());
                revalidate();
                repaint();
            }
        }
    }

}
