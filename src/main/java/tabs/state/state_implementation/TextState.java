package tabs.state.state_implementation;

import repository.graff_components.GraffNodeComposite;
import tabs.elements.element_implementation.TextElement;
import tabs.state.ToolState;
import tabs.state.slide.rightbar.SlideController;
import tabs.undoredo.command_implementation.AddCommand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class TextState implements ToolState {
    @Override
    public void mousePressed(MouseEvent e, SlideController controller) {
        TextElement te = new TextElement(controller.getSlide(), new Point(e.getX(), e.getY()), new Dimension(50, 50));
        String input = JOptionPane.showInputDialog("Unesite tekst:");

        if (input != null && !input.isEmpty()) {
            te.setText(input);
        }

        AddCommand addCommand = new AddCommand((GraffNodeComposite) controller.getSlide(), te);
        controller.getCommandManager().executeCommand(addCommand);

//        ((GraffNodeComposite)controller.getSlide()).addChild(te);
//        ((GraffTreeImplementation) MainFrame.getInstance().getTree()).addChild(controller.getSlide(), te);
    }


    @Override
    public void mouseDragged(MouseEvent e, SlideController slideController) {}

    @Override
    public void mouseReleased(MouseEvent e, SlideController slideController) {}

    @Override
    public void mouseWheelMoved(MouseWheelEvent e, SlideController slideController) {}
}
