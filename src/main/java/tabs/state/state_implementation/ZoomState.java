package tabs.state.state_implementation;

import tabs.state.ToolState;
import tabs.state.slide.rightbar.SlideController;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class ZoomState implements ToolState {
    private static final double MAX_ZOOM = 3.0;
    private static final double MIN_ZOOM = 0.5;
    private static final double ZOOM_INCREMENT = 0.1;

    @Override
    public void mousePressed(MouseEvent e, SlideController slideController) {}
    @Override
    public void mouseDragged(MouseEvent e, SlideController slideController) {}
    @Override
    public void mouseReleased(MouseEvent e, SlideController slideController) {}

    @Override
    public void mouseWheelMoved(MouseWheelEvent e, SlideController slideController) {
        double scaleFactor = slideController.getSlideView().getCurrentTransform().getScaleX();
        int rotation = e.getWheelRotation();

        if (rotation < 0 && scaleFactor < MAX_ZOOM) {
            scaleFactor += ZOOM_INCREMENT;
        } else if (rotation > 0 && scaleFactor > MIN_ZOOM) {
            scaleFactor -= ZOOM_INCREMENT;
        }

        // prosledi zoom SlideView-u
        slideController.getSlideView().setScaleFactor(scaleFactor);
    }
}
