package tabs.state.state_implementation;

import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import tabs.elements.GraffSlideElement;
import tabs.state.ToolState;
import tabs.state.slide.rightbar.SlideController;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;

public class SelectState implements ToolState {

    @Override
    public void mousePressed(MouseEvent e, SlideController slideController) {
        AffineTransform currentTransform = slideController.getSlideView().getCurrentTransform();
        for (GraffNode child : ((GraffNodeComposite) slideController.getSlide()).getChildren()) {
            GraffSlideElement element = (GraffSlideElement) child;
            Point transformedPoint = transformPoint(e.getPoint(), currentTransform);

            if (element.getLocation().getX() <= transformedPoint.x
                    && transformedPoint.x <= element.getLocation().getX() + element.getDimension().getWidth()
                    && element.getLocation().getY() <= transformedPoint.y
                    && transformedPoint.y <= element.getLocation().getY() + element.getDimension().getHeight()){

                element.setSelected(!element.isSelected());
                break;
            }
        }
    }


    @Override
    public void mouseDragged(MouseEvent e, SlideController slide) {}

    @Override
    public void mouseReleased(MouseEvent e, SlideController slide) {}

    @Override
    public void mouseWheelMoved(MouseWheelEvent e, SlideController slideController) {}
}

