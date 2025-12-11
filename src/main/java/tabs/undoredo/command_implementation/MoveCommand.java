package tabs.undoredo.command_implementation;

import tabs.elements.GraffSlideElement;
import tabs.undoredo.Command;

import java.awt.*;

public class MoveCommand implements Command {

    private GraffSlideElement element;
    private Point oldLocation;
    private Point newLocation;

    public MoveCommand(GraffSlideElement element, Point oldLocation, Point newLocation) {
        this.element = element;
        this.oldLocation = oldLocation;
        this.newLocation = newLocation;
    }

    @Override
    public void execute() {
        element.setLocation(new Point(newLocation));
    }

    @Override
    public void undo() {
        element.setLocation(new Point(oldLocation));
    }
}

