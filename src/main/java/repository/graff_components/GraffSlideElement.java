package repository.graff_components;

public abstract class GraffSlideElement extends GraffLeaf{
    public GraffSlideElement(String title, String author, GraffNode parent) {
        super(title, author, parent);
    }
}
