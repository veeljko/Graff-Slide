package tabs.state.slide.states_selector;

import lombok.Getter;
import raf.graffito.dsw.gui.swing.MainFrame;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import tabs.graffpanel.GraffPanelController;
import tabs.elements.GraffSlideElement;
import tabs.graffpanel.GraffPanelView;
import tabs.state.StateManager;
import tabs.state.slide.rightbar.SlideController;
import tabs.undoredo.CommandManager;
import tabs.undoredo.command_implementation.RotateCommand;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SlideStatesController implements ActionListener {
    @Getter
    private SlideStatesView view = new SlideStatesView(this);
    private StateManager stateManager;
    private CommandManager commandManager;

    public SlideStatesController(StateManager stateManager, CommandManager commandManager) {
        this.stateManager = stateManager;
        this.commandManager = commandManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch (cmd) {
            case "select":
                stateManager.setSelectState();
                break;
            case "move":
                stateManager.setMoveState();
                break;
            case "resize":
                stateManager.setResizeState();
                break;
            case "delete":
                stateManager.setDeleteState();
                break;
            case "rotateleft":
                handleRotate(false);
                updateView();
                break;
            case "rotateright":
                handleRotate(true);
                updateView();
                break;
            case "zoom":
                stateManager.setZoomState();
                break;
            case "undo":
                commandManager.undo();
                updateView();
                break;
            case "redo":
                commandManager.redo();
                updateView();
                break;
        }

        view.repaint();
    }

    private void handleRotate(boolean clockwise){
        GraffPanelController selected = ((GraffPanelView) MainFrame.getInstance().getTabbedPane().getSelectedComponent()).getGraffPanelController();
        ArrayList<Double> oldAngles = new ArrayList<>();
        for (GraffNode node : ((GraffNodeComposite)selected.getSlideController().getSlide()).getChildren()) {
            GraffSlideElement element = (GraffSlideElement) node;
            if (element.isSelected()){
                oldAngles.add(new Double(element.getRotacija()));
            }
        }
        RotateCommand rotateCommand = new RotateCommand();
        int p = 0;
        for (GraffNode node : ((GraffNodeComposite)selected.getSlideController().getSlide()).getChildren()){
            GraffSlideElement element = (GraffSlideElement) node;
            if (element.isSelected()){
                double angle = Math.toRadians(clockwise ? 90 : -90);
                element.rotate(angle);
                rotateCommand.addElement(element, element.getRotacija(), oldAngles.get(p));
                p++;
            }
        }
        commandManager.executeCommand(rotateCommand);
    }


    private void updateView(){
        GraffPanelController selected = ((GraffPanelView) MainFrame.getInstance().getTabbedPane().getSelectedComponent()).getGraffPanelController();
        SlideController slideController = selected.getSlideController();
        slideController.getSlideView().setViewComponents(
                new ArrayList<>(((GraffNodeComposite) slideController.getSlide()).getChildren())
        );
        slideController.getSlideView().validate();
        slideController.getSlideView().repaint();
    }
}
