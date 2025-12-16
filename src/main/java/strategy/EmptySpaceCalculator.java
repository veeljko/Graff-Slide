package strategy;

import lombok.Setter;
import repository.graff_components.GraffNode;

import java.util.ArrayList;
import java.util.List;

public class EmptySpaceCalculator {
    @Setter
    private EmptySpaceStrategy emptySpaceStrategy;

    public EmptySpaceCalculator(EmptySpaceStrategy emptySpaceStrategy) {
        this.emptySpaceStrategy = emptySpaceStrategy;
    }

    public int calculateEmptySpace(ArrayList<GraffNode> elements, int w, int h){
        return emptySpaceStrategy.calculateEmptySpace(elements, w, h);
    }
}
