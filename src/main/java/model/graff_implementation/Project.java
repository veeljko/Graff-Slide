package model.graff_implementation;

import model.graff_components.GraffNode;
import model.graff_components.GraffNodeComposite;

public class Project extends GraffNodeComposite {
    private int number;

    public Project(String title, String author, GraffNode parent) {
        super(title, author, parent);
        number = 1;
        addChild(new Slide("title" + " slide", author, this));
    }
}
