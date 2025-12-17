package tabs.loadimage;

import lombok.Getter;
import tabs.loadimage.control.LoadImageControl;
import tabs.loadimage.proxy.ImageInterface;
import tabs.loadimage.proxy.ProxyImage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@Getter
public class LoadImageView extends JPanel {

    private List<ImageInterface> proxies;
    private List<ImageIcon> thumbnails; // lista umanjenih slika
    private int thumbnailWidth = 50;   // širina umanjene slike
    private int thumbnailHeight = 50;  // visina umanjene slike

    public LoadImageView() {
        this.thumbnails = new ArrayList<>();
        this.proxies = new ArrayList<>();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // vertikalno dodavanje
    }

    // Dodaj novu sliku u listu i repaint
    public void addImage(ImageInterface proxy) {
        proxies.add(proxy);
        BufferedImage bufImg = proxy.display();
        Image scaled = bufImg.getScaledInstance(thumbnailWidth, thumbnailHeight, Image.SCALE_SMOOTH);
        thumbnails.add(new ImageIcon(scaled));
        JButton label = new JButton(thumbnails.get(thumbnails.size() - 1));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        label.addActionListener(new LoadImageControl(proxy));
        add(label);
        revalidate();
        repaint();
    }

    public boolean daLiSadrzi(String filePath){
        for(ImageInterface img: proxies){
            if(filePath.equals(((ProxyImage)img).getFilePath())){
                return true;
            }
        }
        return false;
    }
}

