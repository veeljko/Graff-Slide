package repository.graff_implementation;

import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import repository.graff_components.GraffNodeType;

public class Slide extends GraffNodeComposite {

    public Slide(String title, String author, GraffNode parent) {
        super(title, author, parent);
        super.setType(GraffNodeType.SLIDE);
    }
}
