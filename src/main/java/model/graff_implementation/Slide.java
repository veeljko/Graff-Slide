package model.graff_implementation;

import model.graff_components.GraffLeaf;
import model.graff_components.GraffNode;

public class Slide extends GraffLeaf {

    public Slide(String title, String author, GraffNode parent) {
        super(title, author, parent);
    }
}
