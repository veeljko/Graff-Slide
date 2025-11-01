package repository.graff_components;

import lombok.Getter;
import lombok.Setter;
import repository.graff_implementation.Workspace;
import java.util.List;

@Getter
@Setter
public abstract class GraffNode {
    private GraffNode parent;
    private String title;
    private String author;

    public GraffNode (String title, String author, GraffNode parent) {
        this.parent = parent;
        this.title = title;
        this.author = author;
    }

    private GraffNode getRoot(GraffNode node){
        if (node instanceof Workspace) return node;
        return getRoot(node.getParent());
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
}
