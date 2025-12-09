package tabs;

import lombok.Getter;
import lombok.Setter;
import repository.graff_components.GraffNode;
import tabs.state.StateManager;
import tabs.state.slide.SlideController;
import tabs.state.slide.states_selector.SlideStatesController;
import tabs.state.slide.states_selector.SlideStatesView;

import javax.swing.*;
import java.awt.*;

@Getter

public class GraffPanel extends JPanel {
    private GraffNode node; //ovo je presentation za koji je vezan
    @Setter
    private Color color;
    private Label label1;
    private Label label2;
    private Label label3;
    private JPanel centerPanel;
    private SlideElementsBox slideElementsBox;
    private SlideController slideController;
    @Getter
    private StateManager stateManager = new StateManager();

    public GraffPanel(GraffNode node) {
        super();
        this.node = node;

        setLayout(new BorderLayout());

        label1 = new Label("Presentation: " + node.getTitle()+ " ");
        label2 = new Label("Project: " + node.getParent().getTitle());
        label3 = new Label("Author: " + node.getAuthor());
        JPanel northPanel = new JPanel(new BorderLayout());
        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        textPanel.add(label1);
        textPanel.add(label2);
        textPanel.add(label3);
        northPanel.add(textPanel, BorderLayout.CENTER);

        SlideStatesController slideStatesController = new SlideStatesController(stateManager);
        northPanel.add(slideStatesController.getView(), BorderLayout.NORTH);

        add(northPanel, BorderLayout.NORTH);

        slideElementsBox = new SlideElementsBox();
        add(slideElementsBox, BorderLayout.EAST);


    }

    public void setSlideController(SlideController slideController) {
        this.slideController = slideController;
        // Ukloni prethodni panel
        if (centerPanel != null) {
            remove(centerPanel);
        }

        slideElementsBox.addController(slideController);

        // Novi panel sa fiksnom veličinom i centriranjem
        centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        centerPanel.add(slideController.getSlideView());

        add(centerPanel, BorderLayout.CENTER);

        revalidate(); // da layout ažurira
        repaint();
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
