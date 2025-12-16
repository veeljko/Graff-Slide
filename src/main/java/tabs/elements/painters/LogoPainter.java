package tabs.elements.painters;

import tabs.elements.GraffSlideElement;
import tabs.elements.element_implementation.LogoElement;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class LogoPainter extends PrimordialPainter{
    LogoElement logoElement;

    public LogoPainter(LogoElement logoElement) {
        super(logoElement);
        this.logoElement = logoElement;
        Point p = logoElement.getLocation();
        Dimension d = logoElement.getDimension();

        this.oblik = new Rectangle2D.Double(p.x, p.y, d.width, d.height);
    }

    @Override
    public void paint(Graphics2D g) {
        AffineTransform old = g.getTransform();

        int x = (int) ((double)logoElement.getLocation().x * scaleFactor);
        int y = (int) ((double)logoElement.getLocation().y * scaleFactor);

        int w = (int) ((double)logoElement.getDimension().width * scaleFactor);
        int h = (int) ((double)logoElement.getDimension().height * scaleFactor);

        double origW = 60.0 * scaleFactor;
        double origH = 75.0 * scaleFactor;

        double scaleX = (w / origW) * scaleFactor;
        double scaleY = (h / origH) * scaleFactor;

        double angle = logoElement.getRotacija();

        // --- centar figure u originalnim koordinatama ---
        double centerX = (origW / 2.0) * scaleFactor;
        double centerY = (origH / 2.0) * scaleFactor;

        // 1. translacija na poziciju + centar
        g.translate(x + centerX * scaleX, y + centerY * scaleY);

        // 2. rotacija oko centra
        g.rotate(angle);

        // 3. skaliranje (sada crta lokalno oko centra)
        g.scale(scaleX, scaleY);

        // 4. path centriran oko (0,0) = center
        Path2D logo = new Path2D.Double();
        // Pomeri originalni top-left na (-centerX, -centerY)
        logo.moveTo(30 - centerX, 0 - centerY);
        logo.lineTo(60 - centerX, 30 - centerY);
        logo.lineTo(50 - centerX, 75 - centerY);
        logo.lineTo(10 - centerX, 75 - centerY);
        logo.lineTo(0 - centerX, 30 - centerY);
        logo.closePath();

        // Iscrtavanje
        g.setColor(Color.MAGENTA);
        g.fill(logo);

        g.setColor(Color.BLACK);
        g.draw(logo);

        // Selektovani okvir
        if (logoElement.isSelected()) {
            g.setColor(new Color(0, 150, 0));
            g.drawRect((int)(0 - centerX), (int)(0 - centerY), (int)origW, (int)origH);
        }

        g.setTransform(old);
    }




}
