package tabs.undoredo.command_implementation;

import jtree.GraffTreeImplementation;
import lombok.Getter;
import raf.graffito.dsw.gui.swing.MainFrame;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import tabs.GraffPanel;
import tabs.state.slide.rightbar.SlideController;
import tabs.undoredo.Command;

public class DeleteCommand implements Command {

    private GraffNodeComposite parent;
    @Getter
    private GraffNode element;

    public DeleteCommand(GraffNodeComposite parent, GraffNode element) {
        this.parent = parent;
        this.element = element;
    }

    @Override
    public void execute() {
        parent.removeChild(element);
        SlideController slideController = ((GraffPanel)MainFrame.getInstance().getTabbedPane().getSelectedComponent()).getSlideController();
        ((GraffTreeImplementation) MainFrame.getInstance().getTree()).removeChild(slideController.getSlide(), element);
    }

    @Override
    public void undo() {
        parent.addChild(element);
        SlideController slideController = ((GraffPanel)MainFrame.getInstance().getTabbedPane().getSelectedComponent()).getSlideController();
        ((GraffTreeImplementation) MainFrame.getInstance().getTree()).addChild(slideController.getSlide(), element);
    }
}



