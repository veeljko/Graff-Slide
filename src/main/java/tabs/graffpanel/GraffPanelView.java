package tabs.graffpanel;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.gui.swing.MainFrame;
import repository.graff_components.GraffNode;
import strategy.concretes.FirstEmptySpaceCalculateStrategy;
import strategy.concretes.SecondEmptySpaceCalculateStrategy;
import tabs.state.slide.rightbar.SlideController;
import tabs.state.slide.rightbar.SlideElementsBox;
import tabs.state.slide.states_selector.SlideStatesController;
import tabs.state.slide.states_selector.SlideStatesView;

import javax.swing.*;
import java.awt.*;
@Getter @Setter
public class GraffPanelView extends JPanel {
    @Getter @Setter
    private GraffPanelController graffPanelController;
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
    private JPanel contentPanel = new JPanel();
    @Setter
    private Color color;

    public GraffPanelView(SlideStatesView slideStatesView) {
        super();

        setLayout(new BorderLayout());

        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        contentPanel.setBorder(
                BorderFactory.createEmptyBorder(5, 5, 20, 5)
        );
//
//        add(label1, BorderLayout.NORTH);
//        add(label2, BorderLayout.NORTH);
//        add(label3, BorderLayout.NORTH);

//        SlideStatesController slideStatesController =
//                new SlideStatesController(stateManager, commandManager);
        contentPanel.add(slideStatesView, BorderLayout.CENTER);

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
    }

    public void setSlideController(SlideController slideController){
        // Ukloni prethodni panel
        if (centerPanel != null) {
            remove(centerPanel);
        }
        if (slideElementsBox == null) {
            slideElementsBox = new SlideElementsBox();
            add(slideElementsBox, BorderLayout.EAST);
        }

        slideElementsBox.addController(slideController);

        // Novi panel sa fiksnom veličinom i centriranjem

        centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 2));
        centerPanel.add(slideController.getSlideView());

        add(centerPanel, BorderLayout.CENTER);

        revalidate(); // da layout ažurira
        repaint();
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

    @Override
    public String toString(){
        return graffPanelController.toString();
    }
}
