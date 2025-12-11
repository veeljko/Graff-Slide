package tabs.elements.element_implementation;

import repository.graff_components.GraffNode;
import tabs.elements.GraffSlideElement;

import java.awt.*;

public class LogoElement extends GraffSlideElement {

    public LogoElement(GraffNode parent, Point lokacija, Dimension dimension) {
        super(parent, lokacija, dimension);
    }

    @Override
    public GraffSlideElement kopiraj() {
        Point locationCopy = new Point(getLocation().x, getLocation().y);
        //locationCopy.translate(5, 5);
        LogoElement copy = new LogoElement(
                getParent(),
                locationCopy,
                getDimension());
        return copy;
    }
}
