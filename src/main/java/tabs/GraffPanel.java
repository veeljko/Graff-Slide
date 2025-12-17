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
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
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

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        contentPanel.setBorder(
                BorderFactory.createEmptyBorder(5, 5, 20, 5)
        );


        label1 = new JLabel("Presentation: " + node.getTitle() + " ");
        label2 = new JLabel("Project: " + node.getParent().getTitle() + " ");
        label3 = new JLabel("Author: " + node.getAuthor() + " ");

        add(label1, BorderLayout.NORTH);
        add(label2, BorderLayout.NORTH);
        add(label3, BorderLayout.NORTH);

        SlideStatesController slideStatesController =
                new SlideStatesController(stateManager, commandManager);
        contentPanel.add(slideStatesController.getView(), BorderLayout.CENTER);

        slideElementsBox = new SlideElementsBox();
        add(slideElementsBox, BorderLayout.EAST);

        // radio dugmad – algoritmi
        radioButtonGroup = new ButtonGroup();
        radioButtonAlg1 = new JRadioButton("First Algorithm");
        radioButtonAlg2 = new JRadioButton("Second Algorithm");
        radioButtonAlg1.setSelected(true);

        radioButtonGroup.add(radioButtonAlg1);
        radioButtonGroup.add(radioButtonAlg2);

        contentPanel.add(radioButtonAlg1);
        contentPanel.add(radioButtonAlg2);

        // radio dugmad – ekran
        screenGroup = new ButtonGroup();
        radioButtonSmallScreen = new JRadioButton("Small Screen");
        radioButtonNormalScreen = new JRadioButton("Normal Screen");
        radioButtonNormalScreen.setSelected(true);
        radioButtonFullScreen = new JRadioButton("Full Screen");

        screenGroup.add(radioButtonSmallScreen);
        screenGroup.add(radioButtonNormalScreen);
        screenGroup.add(radioButtonFullScreen);

        contentPanel.add(radioButtonSmallScreen);
        contentPanel.add(radioButtonNormalScreen);
        contentPanel.add(radioButtonFullScreen);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        add(scrollPane, BorderLayout.NORTH);

        radioButtonAlg1.addActionListener(e ->
                emptySpaceCalculator.setEmptySpaceStrategy(
                        new FirstEmptySpaceCalculateStrategy()
                )
        );

        radioButtonAlg2.addActionListener(e ->
                emptySpaceCalculator.setEmptySpaceStrategy(
                        new SecondEmptySpaceCalculateStrategy()
                )
        );

        radioButtonNormalScreen.addActionListener(e -> {
            MainFrame.getInstance().updateSize(1.0);
            if (slideController.getSlideView() != null) slideController.getSlideView().updateWindowSize(1.0);
            slideController.setScaleFactor(1.0);
            slideController.updateView();
        });

        radioButtonSmallScreen.addActionListener(e -> {

            MainFrame.getInstance().updateSize(0.5);
            if (slideController.getSlideView() != null) slideController.getSlideView().updateWindowSize(0.5);
            slideController.setScaleFactor(0.5);
            slideController.updateView();
        });

        radioButtonFullScreen.addActionListener(e -> {
            MainFrame.getInstance().updateSize(1.15);
            if (slideController.getSlideView() != null) slideController.getSlideView().updateWindowSize(1.15);
            slideController.setScaleFactor(1.15);
            slideController.updateView();
            enterFullScreen(MainFrame.getInstance());
        });
    }

    public void enterFullScreen(JFrame frame) {
        GraphicsDevice device =
                GraphicsEnvironment
                        .getLocalGraphicsEnvironment()
                        .getDefaultScreenDevice();

        frame.dispose();

        device.setFullScreenWindow(frame);
        frame.setVisible(true);
    }


    public void setSlideController(SlideController slideController) {
        this.slideController = slideController;
        // Ukloni prethodni panel
        if (centerPanel != null) {
            remove(centerPanel);
        }

        slideElementsBox.addController(slideController);

        // Novi panel sa fiksnom veličinom i centriranjem

        centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 2));
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
