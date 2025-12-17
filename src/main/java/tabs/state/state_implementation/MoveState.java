package tabs.state.state_implementation;

import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import tabs.elements.GraffSlideElement;
import tabs.state.ToolState;
import tabs.state.slide.rightbar.SlideController;
import tabs.undoredo.command_implementation.MoveCommand;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class MoveState implements ToolState {
    private ArrayList<Point> oldLocations = new ArrayList<>();
    private Point lastPoint;

    @Override
    public void mousePressed(MouseEvent e, SlideController slideController) {
        oldLocations.clear();
        AffineTransform currentTransform = slideController.getSlideView().getCurrentTransform();
        lastPoint = transformPoint(e.getPoint(), currentTransform);
        for (GraffNode child : ((GraffNodeComposite) slideController.getSlide()).getChildren()) {
            GraffSlideElement element = (GraffSlideElement) child;
            if (element.isSelected()) {
                oldLocations.add(new Point(element.getLocation()));
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e, SlideController slideController) {
        AffineTransform currentTransform = slideController.getSlideView().getCurrentTransform();
        Point now = transformPoint(e.getPoint(), currentTransform);
        int dx = now.x - lastPoint.x;
        int dy = now.y - lastPoint.y;
        for (GraffNode child : ((GraffNodeComposite) slideController.getSlide()).getChildren()) {
            GraffSlideElement element = (GraffSlideElement) child;
            if (element.isSelected()) {
                int width = element.getDimension().width;
                int height = element.getDimension().height;

                // željene nove koordinate
                int newX = element.getLocation().x + dx;
                int newY = element.getLocation().y + dy;

                // klipovanje na granice SlideView-a
//                newX = Math.max(0, Math.min(newX, slideController.getSlideView().getWidth() - width));
//                newY = Math.max(0, Math.min(newY, slideController.getSlideView().getHeight() - height));

                // izračunaj stvarni pomeraj
                int actualDx = newX - element.getLocation().x;
                int actualDy = newY - element.getLocation().y;

                element.translate(actualDx, actualDy);
            }
        }


        lastPoint = now;
    }

    @Override
    public void mouseReleased(MouseEvent e, SlideController slideController) {
        lastPoint = null;
        int p = 0;
        MoveCommand moveCommand = new MoveCommand();
        for (GraffNode child : ((GraffNodeComposite) slideController.getSlide()).getChildren()) {
            GraffSlideElement element = (GraffSlideElement) child;
            if (element.isSelected()) {
                moveCommand.addElement(element, element.getLocation(), oldLocations.get(p));
                p++;
            }
        }
        slideController.getCommandManager().executeCommand(moveCommand);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e, SlideController slideController) {}
}

