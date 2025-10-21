package model.graff_implementation;

import model.graff_components.GraffNode;
import model.graff_components.GraffNodeComposite;

public class Presentation extends GraffNodeComposite {
    public Presentation(String title, String author, GraffNode parent) {
        super(title, author, parent);
    }
}
