package tabs.elements.element_implementation;

import lombok.Getter;
import lombok.Setter;
import repository.graff_components.GraffNode;
import tabs.elements.GraffSlideElement;

import java.awt.*;

@Getter @Setter
public class TextElement extends GraffSlideElement {

    private String text;
    private Font font = new Font("Arial", Font.PLAIN, 20);

    public TextElement(GraffNode parent, Point lokacija, Dimension dimension, String text) {
        super(parent, lokacija, dimension);
        this.text = text;
    }

    public TextElement(GraffNode parent, Point lokacija, Dimension dimension) {
        super(parent, lokacija, dimension);
        this.text = "";
    }

    @Override
    public GraffSlideElement kopiraj() {
        Point locationCopy = new Point(getLocation().x, getLocation().y);
        //locationCopy.translate(5, 5);
        TextElement copy = new TextElement(
                getParent(),
                locationCopy,
                getDimension());
        return copy;
    }

    public int getFontSize(){
        return font.getSize();
    }

    public void setFontSize(int fontSize){
        font = new Font("Arial", Font.PLAIN, fontSize);
    }
}

