package tabs.state.slide.states_selector;

import lombok.Getter;
import raf.graffito.dsw.gui.swing.MainFrame;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import tabs.GraffPanel;
import tabs.elements.GraffSlideElement;
import tabs.state.StateManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SlideStatesController implements ActionListener {
    @Getter
    private SlideStatesView view = new SlideStatesView(this);
    private StateManager stateManager;

    public SlideStatesController(StateManager stateManager) {
        this.stateManager = stateManager;
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
                System.out.println("rotate left");
                handleRotate(false);
                break;
            case "rotateright":
                handleRotate(true);
                break;
            case "zoom":
                stateManager.setZoomState();
                break;
        }

        view.repaint();
    }

    private void handleRotate(boolean clockwise){
        GraffPanel selected = (GraffPanel) MainFrame.getInstance().getTabbedPane().getSelectedComponent();
        for (GraffNode node : ((GraffNodeComposite)selected.getSlideController().getSlide()).getChildren()){
            GraffSlideElement element = (GraffSlideElement) node;
            if (element.isSelected()){
                double angle = Math.toRadians(clockwise ? 90 : -90);
                element.rotate(angle);
            }
        }
        selected.getSlideController().getSlideView().repaint();
    }
}
