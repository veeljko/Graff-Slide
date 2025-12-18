package tabs.loadimage;

import lombok.Getter;
import tabs.loadimage.control.LoadImageAction;
import tabs.loadimage.model.LoadImageModel;
import tabs.loadimage.proxy.ImageInterface;
import tabs.loadimage.proxy.ProxyImage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@Getter
public class LoadImageView extends JPanel {

    private LoadImageModel model;

    private int thumbnailWidth = 50;   // širina umanjene slike
    private int thumbnailHeight = 50;  // visina umanjene slike


    public LoadImageView(LoadImageModel model) {
        this.model = model;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // vertikalno dodavanje
    }



}

