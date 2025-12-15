package tabs.state.state_implementation;

import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import tabs.elements.GraffSlideElement;
import tabs.state.ToolState;
import tabs.state.slide.rightbar.SlideController;
import tabs.undoredo.command_implementation.DeleteCommand;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class DeleteState implements ToolState {

    @Override
    public void mousePressed(MouseEvent e, SlideController slideController) {
        ArrayList<GraffNode> children = (ArrayList<GraffNode>) ((GraffNodeComposite) slideController.getSlide()).getChildren();

        for (int i = children.size() - 1; i >= 0; i--) {
            GraffSlideElement element = (GraffSlideElement) children.get(i);

            AffineTransform currentTransform = slideController.getSlideView().getCurrentTransform();
            Point transformedPoint = transformPoint(e.getPoint(), currentTransform);

            if (element.getLocation().x <= transformedPoint.x && transformedPoint.x <= element.getLocation().x + element.getDimension().width &&
                    element.getLocation().y <= transformedPoint.y && transformedPoint.y <= element.getLocation().y + element.getDimension().height) {

                GraffNodeComposite parent = (GraffNodeComposite) slideController.getSlide();
                DeleteCommand cmd = new DeleteCommand(parent, element);
                slideController.getCommandManager().executeCommand(cmd);
                break;
            }
        }

    }

    @Override
    public void mouseDragged(MouseEvent e, SlideController slideController) {}

    @Override
    public void mouseReleased(MouseEvent e, SlideController slideController) {}

    @Override
    public void mouseWheelMoved(MouseWheelEvent e, SlideController slideController) {}
}

