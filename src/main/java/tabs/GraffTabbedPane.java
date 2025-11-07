package tabs;

import jtree.nodechangeobserver.INodeChangeSubscriber;
import jtree.nodechangeobserver.NotificationType;
import lombok.Getter;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import repository.graff_components.GraffNodeType;
import repository.graff_implementation.Presentation;
import repository.graff_node_decorator.GraffNodeColorDecorator;
import repository.graff_node_decorator.GraffNodeDecorator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//ova klasa bi vljd trebala da bude ProjectView
public class GraffTabbedPane extends JTabbedPane implements INodeChangeSubscriber {
    @Getter
    private List<Color> reserverColors = new ArrayList<>();
    public GraffTabbedPane() {
        super();
    }

    public void addTabs(GraffNode node){
        //node mora da bude project
        for (GraffNode child : ((GraffNodeComposite) node).getChildren()){
            if (child.getType() == GraffNodeType.PRESENTATION){
                GraffPanel panel = new GraffPanel(child);
                Color color = node.getColor();
                panel.setColor(color);
                reserverColors.add(color);
                addTab(panel);
            }
        }
    }

    private GraffNode getActiveProject(){
        if (getComponentCount() == 0) return null;
        return ((GraffPanel) getComponentAt(0)).getNode().getParent();
    }

    public void addTab(GraffPanel panel){
        addTab(panel.toString(), panel);
        setBackgroundAt(getComponentCount()-1, panel.getColor());
    }


    @Override
    public void update(Object notification, NotificationType type) {
        GraffNode node = null;
        if (notification instanceof GraffNode) node = (GraffNode) notification;


        if (type == NotificationType.EDIT) {
            for (int i = 0; i < getTabCount(); i++) {
                GraffPanel panel = (GraffPanel) getComponentAt(i);

                if (panel.getNode().equals(node)) {
                    panel.update(node);
                    setTitleAt(i, node.getTitle());

                    panel.revalidate();
                    panel.repaint();
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

            if (node.getParent().equals(getActiveProject())) {
                removeTabAt(indexOfTab(node.getTitle()));
            }
            else if (node.equals(getActiveProject())) {
                reserverColors.remove(getActiveProject().getColor());
                removeAll();
            }
            revalidate();
            repaint();
        }
        else if (type == NotificationType.ADD) {
            if (node.getParent().equals(getActiveProject())) {
                GraffPanel panel = new GraffPanel(node);
                panel.setColor(node.getParent().getColor());
                addTab(panel);
                revalidate();
                repaint();
            }
        }
    }

}
