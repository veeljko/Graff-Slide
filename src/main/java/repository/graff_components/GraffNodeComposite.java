package repository.graff_components;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
public abstract class GraffNodeComposite extends GraffNode {
    private List<GraffNode> children = new ArrayList<>();

    public GraffNodeComposite(String title, String author, GraffNode parent) {
        super(title, author, parent);
    }

    private boolean addChildValidation(GraffNode child) {
        if (children.contains(child)) return false;
        if (this.getType() == GraffNodeType.WORKSPACE){
            return child.getType() == GraffNodeType.PROJECT;
        }
        if (this.getType() == GraffNodeType.PROJECT){
            return child.getType() == GraffNodeType.PRESENTATION || child.getType() == GraffNodeType.SLIDE;
        }
        if (this.getType() == GraffNodeType.PRESENTATION){
            return child.getType() == GraffNodeType.SLIDE;
        }
        return false;
    }

    private boolean removeChildValidation(GraffNode child) {
        if (!children.contains(child)) return false;
        if (this.getType() == GraffNodeType.PROJECT){
            return children.size() > 1;
        }
        return true;
    }

    public boolean addChild(GraffNode child) {
        children.add(child);
        return true;
//        if (addChildValidation(child)) {
//            children.add(child);
//            return true;
//        }
//        return false;
    }

    public boolean removeChild(GraffNode child) {
        children.remove(child);
        return true;
//
//        if (removeChildValidation(child)) {
//            children.remove(child);
//            return true;
//        }
//        return false;
    }
}
