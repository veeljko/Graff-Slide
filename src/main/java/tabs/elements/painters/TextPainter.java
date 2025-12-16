package tabs.elements.painters;

import tabs.elements.GraffSlideElement;
import tabs.elements.element_implementation.TextElement;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class TextPainter extends PrimordialPainter {

    private final TextElement textElement;

    public TextPainter(TextElement element) {
        super(element);
        this.textElement = element;
    }

    @Override
    public void paint(Graphics2D g) {
        Graphics2D g2 = (Graphics2D) g.create();

        int x = (int) ((double)element.getLocation().x * scaleFactor);
        int y = (int) ((double)element.getLocation().y * scaleFactor);

        g2.setFont(textElement.getFont());
        FontMetrics fm = g2.getFontMetrics();

        int width = (int) ((double)fm.stringWidth(textElement.getText()) * scaleFactor);
        int height = (int) ((double)fm.getHeight() * scaleFactor);

        // update element dimension
        textElement.getDimension().setSize(width, height);

        // centar rotacije
        double cx = x + width / 2.0;
        double cy = y - fm.getAscent() + height / 2.0;


        // primena rotacije
        g2.rotate(textElement.getRotacija(), cx, cy);

        // CRTAJ TEKST
        g2.setColor(Color.BLACK);
        g2.drawString(textElement.getText(), x, y);

        // okvir selekcije
        if (textElement.isSelected()) {
            g.setColor(new Color(0, 150, 0));
            g.setStroke(new BasicStroke(2));
            g.drawRect(x - 2, y - fm.getAscent(), width + 4, height + 4);
        }

        g2.dispose();
    }

}

