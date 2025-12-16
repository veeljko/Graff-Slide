package tabs.state.slide;

import lombok.Getter;
import lombok.Setter;
import repository.graff_components.GraffNode;
import tabs.elements.GraffSlideElement;
import tabs.elements.element_implementation.ImageElement;
import tabs.elements.element_implementation.LogoElement;
import tabs.elements.element_implementation.TextElement;
import tabs.elements.painters.*;
import tabs.elements.painters.Painter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

@Getter @Setter
public class SlideView extends JPanel {
    private int windowWidth = 650;
    private int windowHeight = 450;
    private final Dimension size = new Dimension(windowWidth, windowHeight);

    private AffineTransform currentTransform = new AffineTransform();
    private ArrayList<GraffNode> viewComponents = new ArrayList<>();
    private double scaleFactor = 1;

    public SlideView() {
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setTransform(currentTransform);

        // Crtanje svih elemenata
        for (GraffNode child : viewComponents) {
            GraffSlideElement element = (GraffSlideElement) child;

            PrimordialPainter painter = null;

            if (element instanceof ImageElement) {
                painter = new ImagePainter((ImageElement) element);
            } else if (element instanceof LogoElement) {
                painter = new LogoPainter((LogoElement) element);
            }
            else if (element instanceof TextElement) {
                painter = new TextPainter((TextElement) element);
            }
            // Dodaj ostale tipove elemenata po potrebi

            if (painter != null) {
                painter.setScaleFactor(scaleFactor);
                painter.paint(g2d);
            }
        }
    }

    public void setScaleFactor(double scaleFactor) {
        currentTransform = new AffineTransform();
        currentTransform.scale(scaleFactor, scaleFactor);
    }

    public void setScaleFactorWindow(double scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    public void updateWindowSize(double factor){
        Dimension newSize = new Dimension((int) (windowWidth*factor), (int) (windowHeight*factor));
        setPreferredSize(newSize);
        setMinimumSize(newSize);
        setMaximumSize(newSize);
    }
}
