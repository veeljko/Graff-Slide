package tabs.undoredo.command_implementation;

import tabs.elements.GraffSlideElement;
import tabs.undoredo.Command;

public class RotateCommand implements Command {

    private GraffSlideElement element;
    private double oldAngle;
    private double newAngle;

    public RotateCommand(GraffSlideElement element, double oldAngle, double newAngle) {
        this.element = element;
        this.oldAngle = oldAngle;
        this.newAngle = newAngle;
    }

    @Override
    public void execute() {
        element.setRotacija(newAngle);
    }

    @Override
    public void undo() {
        element.setRotacija(oldAngle);
    }
}

