package tabs;

import jtree.model.GraffTreeItem;
import jtree.nodechangeobserver.INodeChangeSubscriber;
import lombok.Getter;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
public class GraffPanel extends JPanel {
    private GraffNode node; //ovo je presentation za koji je vezan
    private Color color;
    private Random rand = new Random();
    private Label label1;
    private Label label2;
    private Label label3;

    public GraffPanel(GraffNode node) {
        super();
        this.node = node;
        this.color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));

        label1 = new Label("Presentation: " + node.getTitle()+ " ");
        label2 = new Label("Project: " + node.getParent().getTitle());
        label3 = new Label("Author: " + node.getAuthor());
        add(label1);
        add(label2);
        add(label3);
    }

    @Override
    public String toString() {
        return node.toString();
    }

    public void update(GraffNode node) {
        this.node = node;
        label1.setText("Presentation: " + node.getTitle() + " ");
        label2.setText("Project: " + node.getParent().getTitle());
        label3.setText("Author: " + node.getAuthor());
        revalidate();
        repaint();
    }

    public void update(String project){
        label2.setText("Project: " + project);
        revalidate();
        repaint();
    }
}
