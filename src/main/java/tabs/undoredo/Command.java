package tabs.undoredo;

import repository.graff_components.GraffNode;
import tabs.state.slide.SlideController;
import tabs.state.slide.SlideView;

public interface Command {
    void execute(); // Izvršavanje akcije
    void undo();    // Poništavanje akcije
}
