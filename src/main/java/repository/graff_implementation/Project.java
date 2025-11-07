package repository.graff_implementation;

import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import repository.graff_components.GraffNodeType;

public class Project extends GraffNodeComposite {
    private int number;

    public Project(String title, String author, GraffNode parent) {
        super(title, author, parent);
        number = 1;
        addChild(new Slide("title" + " slide", author, this));
        super.setType(GraffNodeType.PROJECT);
    }
}
