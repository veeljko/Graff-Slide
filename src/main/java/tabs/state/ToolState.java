package tabs.state;

import tabs.state.slide.rightbar.SlideController;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public interface ToolState {
    void mousePressed(MouseEvent e, SlideController slideController);
    void mouseDragged(MouseEvent e, SlideController slideController);
    void mouseReleased(MouseEvent e, SlideController slideController);
    void mouseWheelMoved(MouseWheelEvent e, SlideController slideController);

    public default Point transformPoint(Point p, AffineTransform currentTransform) {
        try {
            AffineTransform inverseTransform = currentTransform.createInverse();
            Point2D.Double src = new Point2D.Double(p.x, p.y);
            Point2D.Double dest = new Point2D.Double();
            inverseTransform.transform(src, dest);
            return new Point((int) dest.x, (int) dest.y);
        } catch (Exception e) {
            return p;
        }
    }

}
