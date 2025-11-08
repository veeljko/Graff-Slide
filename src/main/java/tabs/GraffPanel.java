package tabs;

import lombok.Getter;
import lombok.Setter;
import repository.graff_components.GraffNode;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

@Getter
@Setter
//ova klasa bi vljd trebala da bude PresentationView
public class GraffPanel extends JPanel {
    private GraffNode node; //ovo je presentation za koji je vezan
    private Color color;
    private Label label1;
    private Label label2;
    private Label label3;

    public GraffPanel(GraffNode node) {
        super();
        this.node = node;

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
