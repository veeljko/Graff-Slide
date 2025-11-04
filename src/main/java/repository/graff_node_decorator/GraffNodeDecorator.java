package repository.graff_node_decorator;

import repository.graff_components.GraffNode;

public abstract class GraffNodeDecorator extends GraffNode {
    private GraffNode graffNode;

    public GraffNodeDecorator(GraffNode graffNode) {
        super(graffNode.getTitle(), graffNode.getAuthor(), graffNode.getParent());
        this.graffNode = graffNode;
    }

    public GraffNode getBaseGraffNode(){
        if (graffNode instanceof GraffNodeDecorator) return ((GraffNodeDecorator) graffNode).getBaseGraffNode();
        return graffNode;
    }
}
