package tabs;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.gui.swing.MainFrame;
import repository.graff_components.GraffNode;
import strategy.EmptySpaceCalculator;
import strategy.concretes.FirstEmptySpaceCalculateStrategy;
import strategy.concretes.SecondEmptySpaceCalculateStrategy;
import tabs.state.StateManager;
import tabs.state.slide.rightbar.SlideController;
import tabs.state.slide.rightbar.SlideElementsBox;
import tabs.state.slide.states_selector.SlideStatesController;
import tabs.undoredo.CommandManager;

import javax.swing.*;
import java.awt.*;

@Getter
//REMINDER!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//RAZDVOJI VIEW KOMPONENTE U POSEBNU KLASU!!!!!!!!!!!
public class GraffPanel extends JPanel {
    private GraffNode node; //ovo je presentation za koji je vezan
    @Setter
    private Color color;
    private Label label1;
    private Label label2;
    private Label label3;
    private ButtonGroup radioButtonGroup;
    private JRadioButton radioButtonAlg1;
    private JRadioButton radioButtonAlg2;
    private ButtonGroup screenGroup;
    private JRadioButton radioButtonSmallScreen;
    private JRadioButton radioButtonNormalScreen;
    private JRadioButton radioButtonFullScreen;
    private JPanel centerPanel;
    private SlideElementsBox slideElementsBox;
    private SlideController slideController;
    @Getter
    private StateManager stateManager = new StateManager();
    private CommandManager commandManager = new CommandManager();
    @Getter
    private EmptySpaceCalculator emptySpaceCalculator = new EmptySpaceCalculator(new FirstEmptySpaceCalculateStrategy());

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

        SlideStatesController slideStatesController = new SlideStatesController(stateManager, commandManager);
        northPanel.add(slideStatesController.getView(), BorderLayout.NORTH);

        add(northPanel, BorderLayout.NORTH);

        slideElementsBox = new SlideElementsBox();
        add(slideElementsBox, BorderLayout.EAST);


        radioButtonGroup = new ButtonGroup();
        radioButtonAlg1 = new JRadioButton("First Algorithm");
        radioButtonAlg2 = new JRadioButton("Second Algorithm");
        radioButtonAlg1.setSelected(true);
        textPanel.add(radioButtonAlg1);
        textPanel.add(radioButtonAlg2);
        radioButtonGroup.add(radioButtonAlg1);
        radioButtonGroup.add(radioButtonAlg2);

        screenGroup = new ButtonGroup();
        radioButtonSmallScreen = new JRadioButton("Small Screen");
        radioButtonNormalScreen = new JRadioButton("Normal Screen");
        radioButtonNormalScreen.setSelected(true);
        radioButtonFullScreen = new JRadioButton("Full Screen");

        textPanel.add(radioButtonSmallScreen);
        textPanel.add(radioButtonNormalScreen);
        textPanel.add(radioButtonFullScreen);
        screenGroup.add(radioButtonSmallScreen);
        screenGroup.add(radioButtonNormalScreen);
        radioButtonGroup.add(radioButtonFullScreen);



        radioButtonAlg1.addActionListener(e -> {
            emptySpaceCalculator.setEmptySpaceStrategy(new FirstEmptySpaceCalculateStrategy());
        });
        radioButtonAlg2.addActionListener(e -> {
            emptySpaceCalculator.setEmptySpaceStrategy(new SecondEmptySpaceCalculateStrategy());
        });
        radioButtonNormalScreen.addActionListener(e -> {
            MainFrame.getInstance().updateSize(1.0);
            slideController.getSlideView().updateWindowSize(1.0);
            slideController.setScaleFactor(1.0);
            slideController.updateView();
        });
        radioButtonSmallScreen.addActionListener(e -> {
            MainFrame.getInstance().updateSize(0.5);
            slideController.getSlideView().updateWindowSize(0.5);
            slideController.setScaleFactor(0.5);
            slideController.updateView();
        });

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
