package strategy.concretes;

import repository.graff_components.GraffNode;
import strategy.EmptySpaceStrategy;
import tabs.elements.GraffSlideElement;

import java.util.ArrayList;

public class FirstEmptySpaceCalculateStrategy implements EmptySpaceStrategy {
    @Override
    public int calculateEmptySpace(ArrayList<GraffNode> elements, int w, int h) {
        int povrsinaSlide = w * h;
        int povrsinaElemenata = 0;
        for (GraffNode child : elements) {
            GraffSlideElement element = (GraffSlideElement) child;
            povrsinaElemenata += (element.getDimension().width * element.getDimension().height);
        }
        return Math.max(povrsinaSlide - povrsinaElemenata, 0);
    }
}
