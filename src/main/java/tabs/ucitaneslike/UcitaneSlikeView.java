package tabs.ucitaneslike;

import lombok.Getter;
import raf.graffito.dsw.gui.swing.MainFrame;
import tabs.ucitaneslike.control.UcitajSlikuControl;
import tabs.ucitaneslike.proxy.ImageInterfejs;
import tabs.ucitaneslike.proxy.ProxyImage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@Getter
public class UcitaneSlikeView extends JPanel {

    private List<ImageInterfejs> proxies;
    private List<ImageIcon> thumbnails; // lista umanjenih slika
    private int thumbnailWidth = 50;   // širina umanjene slike
    private int thumbnailHeight = 50;  // visina umanjene slike

    public UcitaneSlikeView() {
        this.thumbnails = new ArrayList<>();
        this.proxies = new ArrayList<>();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // vertikalno dodavanje
    }

    // Dodaj novu sliku u listu i repaint
    public void addImage(ImageInterfejs proxy) {
        proxies.add(proxy);
        BufferedImage bufImg = proxy.display();
        Image scaled = bufImg.getScaledInstance(thumbnailWidth, thumbnailHeight, Image.SCALE_SMOOTH);
        thumbnails.add(new ImageIcon(scaled));
        JButton label = new JButton(thumbnails.get(thumbnails.size() - 1));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        label.addActionListener(new UcitajSlikuControl(proxy));
        add(label);
        revalidate();
        repaint();
    }

    public boolean daLiSadrzi(String filePath){
        for(ImageInterfejs img: proxies){
            if(filePath.equals(((ProxyImage)img).getFilePath())){
                return true;
            }
        }
        return false;
    }
}

