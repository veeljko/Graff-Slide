package tabs.graffpanel;

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
public class GraffPanelController {
    private GraffNode node; //ovo je presentation za koji je vezan (model)
    private GraffPanelView view; // view
    private SlideController slideController;
    @Getter
    private StateManager stateManager = new StateManager();
    private CommandManager commandManager = new CommandManager();
    @Getter
    private EmptySpaceCalculator emptySpaceCalculator = new EmptySpaceCalculator(new FirstEmptySpaceCalculateStrategy());

    public GraffPanelController(GraffNode node) {
        super();
        this.node = node;
        SlideStatesController slideStatesController =
                new SlideStatesController(stateManager, commandManager);
        view = new GraffPanelView(slideStatesController.getView());
        view.setGraffPanelController(this);
        addListeners();
    }


    public void setSlideController(SlideController slideController) {
        this.slideController = slideController;
        view.setSlideController(slideController);
    }

    @Override
    public String toString() {
        return node.toString();
    }

    public void update(GraffNode node) {
        this.node = node;
        view.getLabel1().setText("Presentation: " + node.getTitle() + " ");
        view.getLabel2().setText("Project: " + node.getParent().getTitle());
        view.getLabel3().setText("Author: " + node.getAuthor());
        view.revalidate();
        view.repaint();
    }

    public void update(String project){
        view.getLabel2().setText("Project: " + project);
        view.revalidate();
        view.repaint();
    }

    private void addListeners(){
        view.getRadioButtonAlg1().addActionListener(e ->
                emptySpaceCalculator.setEmptySpaceStrategy(
                        new FirstEmptySpaceCalculateStrategy()
                )
        );

        view.getRadioButtonAlg2().addActionListener(e ->
                emptySpaceCalculator.setEmptySpaceStrategy(
                        new SecondEmptySpaceCalculateStrategy()
                )
        );

        view.getRadioButtonNormalScreen().addActionListener(e -> {
            MainFrame.getInstance().updateSize(1.0);
            if (slideController.getSlideView() != null) slideController.getSlideView().updateWindowSize(1.0);
            slideController.setScaleFactor(1.0);
            slideController.updateView();
        });

        view.getRadioButtonSmallScreen().addActionListener(e -> {

            MainFrame.getInstance().updateSize(0.5);
            if (slideController.getSlideView() != null) slideController.getSlideView().updateWindowSize(0.5);
            slideController.setScaleFactor(0.5);
            slideController.updateView();
        });

        view.getRadioButtonFullScreen().addActionListener(e -> {
            MainFrame.getInstance().updateSize(1.15);
            if (slideController.getSlideView() != null) slideController.getSlideView().updateWindowSize(1.15);
            slideController.setScaleFactor(1.15);
            slideController.updateView();
            view.enterFullScreen(MainFrame.getInstance());
        });
    }


}
