package tabs.undoredo.command_implementation;

import tabs.elements.GraffSlideElement;
import tabs.undoredo.Command;

import java.awt.*;

public class ResizeCommand implements Command {

    private GraffSlideElement element;
    private Dimension oldDim;
    private Dimension newDim;

    public ResizeCommand(GraffSlideElement element, Dimension oldDim, Dimension newDim) {
        this.element = element;
        this.oldDim = new Dimension(oldDim);
        this.newDim = new Dimension(newDim);
    }

    @Override
    public void execute() {
        element.setDimension(new Dimension(newDim));
    }

    @Override
    public void undo() {
        element.setDimension(new Dimension(oldDim));
    }
}
