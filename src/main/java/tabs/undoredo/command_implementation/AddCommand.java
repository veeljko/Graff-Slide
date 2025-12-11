package tabs.undoredo.command_implementation;

import jtree.GraffTreeImplementation;
import lombok.Getter;
import raf.graffito.dsw.gui.swing.MainFrame;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import tabs.GraffPanel;
import tabs.state.slide.SlideController;
import tabs.undoredo.Command;

public class AddCommand implements Command {
    private GraffNodeComposite parent;
    private GraffNode element;

    public AddCommand(GraffNodeComposite parent, GraffNode element) {
        this.parent = parent;
        this.element = element;
    }

    @Override
    public void execute() {
        parent.addChild(element);
        SlideController slideController = ((GraffPanel) MainFrame.getInstance().getTabbedPane().getSelectedComponent()).getSlideController();
        ((GraffTreeImplementation) MainFrame.getInstance().getTree()).addChild(slideController.getSlide(), element);
    }

    @Override
    public void undo() {
        parent.removeChild(element);
        SlideController slideController = ((GraffPanel) MainFrame.getInstance().getTabbedPane().getSelectedComponent()).getSlideController();
        ((GraffTreeImplementation) MainFrame.getInstance().getTree()).removeChild(slideController.getSlide(), element);
    }
}
