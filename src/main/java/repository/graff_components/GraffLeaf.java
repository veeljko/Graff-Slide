package repository.graff_components;

public abstract class GraffLeaf extends GraffNode{
    public GraffLeaf(String title, String author, GraffNode parent) {
        super(title, author, parent);
    }

    public String getTitle(){
        return super.getTitle();
    }
}
