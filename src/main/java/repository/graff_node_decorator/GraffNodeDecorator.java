package repository.graff_node_decorator;

import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import repository.graff_components.GraffNodeType;

import java.util.List;
import java.util.Objects;

public abstract class GraffNodeDecorator extends GraffNodeComposite {
    private GraffNode graffNode;

    public GraffNodeDecorator(GraffNode graffNode) {
        super(graffNode.getTitle(), graffNode.getAuthor(), graffNode.getParent());
        this.graffNode = graffNode;
    }

    public List<GraffNode> getChildren() {
        return ((GraffNodeComposite) graffNode).getChildren();
    }

    public GraffNodeType getType(){
        return graffNode.getType();
    }

    public boolean addChild(GraffNode child){
        if (graffNode instanceof GraffNodeComposite){
            return ((GraffNodeComposite) graffNode).addChild(child);
        }
        return false;
    }

    public boolean removeChild(GraffNode child){
        if (graffNode instanceof GraffNodeComposite){
            return ((GraffNodeComposite) graffNode).removeChild(child);
        }
        return false;
    }

    public String getTitle(){
        return graffNode.getTitle();
    }

    public void setTitle(String title){
        graffNode.setTitle(title);
    }

    public String getAuthor(){
        return graffNode.getAuthor();
    }

    public void setAuthor(String author){
        graffNode.setAuthor(author);
    }

    @Override
    public String toString() { return graffNode.getTitle(); }

    @Override
    public boolean equals(Object o) {
        if (o instanceof GraffNode) {
            GraffNode node = (GraffNode) o;
            return node.getTitle().equals(getTitle()) && node.getAuthor().equals(getAuthor()) && node.getType() == graffNode.getType();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(graffNode);
    }
}
