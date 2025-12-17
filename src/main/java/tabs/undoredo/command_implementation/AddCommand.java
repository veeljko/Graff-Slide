package tabs.undoredo.command_implementation;

import jtree.GraffTreeImplementation;
import raf.graffito.dsw.gui.swing.MainFrame;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import tabs.graffpanel.GraffPanelController;
import tabs.graffpanel.GraffPanelView;
import tabs.state.slide.rightbar.SlideController;
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
        ((GraffTreeImplementation) MainFrame.getInstance().getTree()).addChild(parent, element);
    }

    @Override
    public void undo() {
        parent.removeChild(element);
        ((GraffTreeImplementation) MainFrame.getInstance().getTree()).removeChild(parent, element);
    }
}
