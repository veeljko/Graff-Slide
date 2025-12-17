package tabs.state.state_implementation;

import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import tabs.elements.GraffSlideElement;
import tabs.elements.element_implementation.TextElement;
import tabs.state.ToolState;
import tabs.state.slide.rightbar.SlideController;
import tabs.undoredo.command_implementation.ResizeImageCommand;
import tabs.undoredo.command_implementation.ResizeTextCommand;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;


public class ResizeState implements ToolState {
    private ArrayList<Dimension> oldDimensions = new ArrayList<>();
    private ArrayList<Integer> oldFontSizes = new ArrayList<>();
    private Point lastPoint;

    @Override
    public void mousePressed(MouseEvent e, SlideController slideController) {
        oldDimensions.clear();
        oldFontSizes.clear();
        AffineTransform currentTransform = slideController.getSlideView().getCurrentTransform();
        lastPoint = transformPoint(e.getPoint(), currentTransform);
        for (GraffNode child : ((GraffNodeComposite) slideController.getSlide()).getChildren()) {
            GraffSlideElement element = (GraffSlideElement) child;
            if (element.isSelected()) {
                if (element instanceof TextElement) oldFontSizes.add(((TextElement) element).getFontSize());
                else oldDimensions.add(new Dimension(element.getDimension()));
            }
        }
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
                if (element instanceof TextElement te) {

                    int newFontSize = te.getFontSize() + dy / 2;
                    newFontSize = Math.max(8, newFontSize);

                    te.setFontSize(newFontSize);
                }
                else {
                    int newWidth = element.getDimension().width + dx;
                    int newHeight = element.getDimension().height + dy;

                    // ograniči minimum dimenzija (npr. 10x10)
                    newWidth = Math.max(newWidth, 10);
                    newHeight = Math.max(newHeight, 10);

                    // ograniči da element ne izađe iz prozora
//                    int x = element.getLocation().x;
//                    int y = element.getLocation().y;
//
//                    if (x + newWidth > viewWidth) {
//                        newWidth = viewWidth - x;
//                    }
//                    if (y + newHeight > viewHeight) {
//                        newHeight = viewHeight - y;
//                    }

                    element.setDimension(new Dimension(newWidth, newHeight));
                }
            }
        }

        lastPoint = now;
    }


    @Override
    public void mouseReleased(MouseEvent e, SlideController slideController) {
        int p1 = 0, p2 = 0;
        ResizeImageCommand resizeImageCommand = new ResizeImageCommand();
        ResizeTextCommand resizeTextCommand = new ResizeTextCommand();
        for (GraffNode child : ((GraffNodeComposite) slideController.getSlide()).getChildren()) {
            GraffSlideElement element = (GraffSlideElement) child;
            if (element.isSelected()) {
                if (element instanceof TextElement te) resizeTextCommand.addElement(element, ((TextElement) element).getFontSize(), oldFontSizes.get(p1++));
                else resizeImageCommand.addElement(element, element.getDimension(), oldDimensions.get(p2++));
            }
        }
        slideController.getCommandManager().executeCommand(resizeImageCommand);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e, SlideController slideController) {}
}
