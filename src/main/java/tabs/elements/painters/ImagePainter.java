package tabs.elements.painters;

import tabs.elements.element_implementation.ImageElement;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class ImagePainter extends PrimordialPainter{
    private ImageElement image;

    public ImagePainter(ImageElement image) {
        super(image);
        this.image = image;
    }

    @Override
    public void paint(Graphics2D g) {
        AffineTransform old = g.getTransform();

        // centar elementa za rotaciju
        double cx = image.getLocation().x + image.getDimension().width / 2.0;
        double cy = image.getLocation().y + image.getDimension().height / 2.0;

        // primeni rotaciju oko centra
        g.rotate(image.getRotacija(), cx, cy);

        // nacrtaj sliku
        g.drawImage(
                image.getImage(),
                image.getLocation().x,
                image.getLocation().y,
                image.getDimension().width,
                image.getDimension().height,
                null
        );

        // ako je selektovan, nacrtaj okvir
        if (image.isSelected()) {
            g.setColor(new Color(0, 150, 0));
            g.setStroke(new BasicStroke(3));
            g.drawRect(
                    image.getLocation().x - 2,
                    image.getLocation().y - 2,
                    image.getDimension().width + 4,
                    image.getDimension().height + 4
            );
        }

        // vrati staru transformaciju
        g.setTransform(old);
    }
}
