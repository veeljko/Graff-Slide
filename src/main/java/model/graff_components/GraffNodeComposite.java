package model.graff_components;

import lombok.Getter;
import model.graff_implementation.Presentation;
import model.graff_implementation.Project;
import model.graff_implementation.Slide;
import model.graff_implementation.Workspace;

import java.util.ArrayList;
import java.util.List;
@Getter
public abstract class GraffNodeComposite extends GraffNode {
    private List<GraffNode> children = new ArrayList<GraffNode>();

    public GraffNodeComposite(String title, String author, GraffNode parent) {
        super(title, author, parent);
    }

    private boolean addChildValidation(GraffNode child) {
        if (children.contains(child)) return false;
        if (this instanceof Workspace){
            return child instanceof Project;
        }
        if (this instanceof Project){
            return child instanceof Presentation || child instanceof Slide;
        }
        if (this instanceof Presentation){
            return child instanceof Slide;
        }
        return false;
    }

    private boolean removeChildValidation(GraffNode child) {
        if (!children.contains(child)) return false;
        if (this instanceof Project){
            return children.size() > 1;
        }
        return true;
    }

    public boolean addChild(GraffNode child) {
        if (addChildValidation(child)) {
            children.add(child);
            return true;
        }
        return false;
    }

    public boolean removeChild(GraffNode child) {
        if (removeChildValidation(child)) {
            children.remove(child);
            return true;
        }
        return false;
    }
}
