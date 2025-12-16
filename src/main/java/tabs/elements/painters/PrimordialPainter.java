package tabs.elements.painters;


import lombok.Getter;
import lombok.Setter;
import tabs.elements.GraffSlideElement;

import java.awt.*;
@Getter
public abstract class PrimordialPainter implements Painter {

    public GraffSlideElement element;
    public Shape oblik;
    @Setter
    public double scaleFactor = 1;

    public PrimordialPainter(GraffSlideElement element) {
        this.element = element;
    }

    public boolean elementAt(Point p) {
        return oblik != null && oblik.contains(p);
    }
}