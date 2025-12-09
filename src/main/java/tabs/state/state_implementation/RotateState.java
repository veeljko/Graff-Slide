package tabs.state.state_implementation;

import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import repository.graff_implementation.Slide;
import tabs.elements.GraffSlideElement;
import tabs.state.ToolState;
import tabs.state.slide.SlideController;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;

public class RotateState implements ToolState {

    private Point last;

    @Override
    public void mousePressed(MouseEvent e, SlideController slideController) {
        AffineTransform currentTransform = slideController.getSlideView().getCurrentTransform();
        last = transformPoint(e.getPoint(), currentTransform);
    }

    @Override
    public void mouseDragged(MouseEvent e, SlideController slideController) {
        AffineTransform currentTransform = slideController.getSlideView().getCurrentTransform();
        Point now = transformPoint(e.getPoint(), currentTransform);

        double angle = Math.toRadians(90);

        for (GraffNode child : ((GraffNodeComposite) slideController.getSlide()).getChildren()){
            GraffSlideElement element = (GraffSlideElement) child;
            if (element.isSelected()){
                element.rotate(angle);
            }
        }

        last = now;
    }

    @Override
    public void mouseReleased(MouseEvent e, SlideController slideController) {}

    @Override
    public void mouseWheelMoved(MouseWheelEvent e, SlideController slideController) {}
}

