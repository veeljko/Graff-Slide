package repository.graff_components;

import lombok.Getter;
import lombok.Setter;
import repository.graff_implementation.Workspace;

import java.awt.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public abstract class GraffNode {
    private GraffNode parent;
    private String title;
    private String author;
    private GraffNodeType type;

    public GraffNode (String title, String author, GraffNode parent) {
        this.parent = parent;
        this.title = title;
        this.author = author;
    }

    private GraffNode getRoot(GraffNode node){
        if (node instanceof Workspace) return node;
        return getRoot(node.getParent());
    }

    public Color getColor(){
        return Color.white;
    }

    public GraffNode findByName(GraffNode node, String name){
        if (node.getTitle().equals(name)) return node;
        if (node instanceof GraffNodeComposite){
            List<GraffNode> children = ((GraffNodeComposite) node).getChildren();
            GraffNode result = null;
            for (GraffNode child : children) {
                result = findByName(child, name);
                if (result != null) return result;
            }
        }
        return null;
    }

    public GraffNode findByName(String name) {
        GraffNode root = getRoot(this);
        return findByName(root, name);
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof GraffNode) {
            GraffNode node = (GraffNode) o;
            return node.getTitle().equals(getTitle()) && node.getAuthor().equals(getAuthor()) && node.getType() == getType();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, title, author, type);
    }
}
