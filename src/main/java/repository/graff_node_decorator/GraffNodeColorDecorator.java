package repository.graff_node_decorator;

import lombok.Getter;
import repository.graff_components.GraffNode;

import java.awt.*;
@Getter
public class GraffNodeColorDecorator extends GraffNodeDecorator{
    private Color color;
    public GraffNodeColorDecorator(GraffNode graffNode, Color color) {
        super(graffNode);
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
