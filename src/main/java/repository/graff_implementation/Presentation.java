package repository.graff_implementation;

import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import repository.graff_components.GraffNodeType;

public class Presentation extends GraffNodeComposite {
    public Presentation(String title, String author, GraffNode parent) {
        super(title, author, parent);
        super.setType(GraffNodeType.PRESENTATION);
    }
}
