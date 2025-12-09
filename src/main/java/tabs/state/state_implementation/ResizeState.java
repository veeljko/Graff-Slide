package tabs.state.state_implementation;

import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import tabs.elements.GraffSlideElement;
import tabs.state.ToolState;
import tabs.state.slide.SlideController;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;


public class ResizeState implements ToolState {

    private Point lastPoint;

    @Override
    public void mousePressed(MouseEvent e, SlideController slideController) {
        AffineTransform currentTransform = slideController.getSlideView().getCurrentTransform();
        lastPoint = transformPoint(e.getPoint(), currentTransform);
    }

    @Override
    public void mouseDragged(MouseEvent e, SlideController slideController) {
        AffineTransform currentTransform = slideController.getSlideView().getCurrentTransform();
        Point now = transformPoint(e.getPoint(), currentTransform);

        int dx = now.x - lastPoint.x;
        int dy = now.y - lastPoint.y;

        int viewWidth = slideController.getSlideView().getWidth();
        int viewHeight = slideController.getSlideView().getHeight();

        for (GraffNode child : ((GraffNodeComposite) slideController.getSlide()).getChildren()) {
            GraffSlideElement element = (GraffSlideElement) child;

            if (element.isSelected()) {
                int newWidth = element.getDimension().width + dx;
                int newHeight = element.getDimension().height + dy;

                // ograniči minimum dimenzija (npr. 10x10)
                newWidth = Math.max(newWidth, 10);
                newHeight = Math.max(newHeight, 10);

                // ograniči da element ne izađe iz prozora
                int x = element.getLocation().x;
                int y = element.getLocation().y;

                if (x + newWidth > viewWidth) {
                    newWidth = viewWidth - x;
                }
                if (y + newHeight > viewHeight) {
                    newHeight = viewHeight - y;
                }

                element.setDimension(new Dimension(newWidth, newHeight));
            }
        }

        lastPoint = now;
    }


    @Override
    public void mouseReleased(MouseEvent e, SlideController slideController) {}

    @Override
    public void mouseWheelMoved(MouseWheelEvent e, SlideController slideController) {}
}
