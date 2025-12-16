package strategy;

import repository.graff_components.GraffNode;
import tabs.elements.GraffSlideElement;

import java.util.ArrayList;

public interface EmptySpaceStrategy {
    int calculateEmptySpace(ArrayList<GraffNode> elements, int w, int h);
}
