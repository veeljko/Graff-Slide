package tabs;

import javax.swing.*;
import java.awt.*;

public class SlideView extends JPanel {
    private String text;
    private static Dimension size = new Dimension(350, 250);

    public SlideView(String text) {
        this.text = text;

        // Obavezno postavljamo fiksne dimenzije
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString(text, 40, 40);
    }
}
