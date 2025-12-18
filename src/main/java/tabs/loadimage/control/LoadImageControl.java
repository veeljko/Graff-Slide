package tabs.loadimage.control;

import lombok.Getter;
import tabs.loadimage.LoadImageView;
import tabs.loadimage.model.LoadImageModel;
import tabs.loadimage.proxy.ImageInterface;
import tabs.loadimage.proxy.ProxyImage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

@Getter
public class LoadImageControl {

    LoadImageView view;
    LoadImageModel model;

    public LoadImageControl(LoadImageView view, LoadImageModel model) {
        this.view = view;
        this.model = model;
    }

    public void addImage(ImageInterface proxy) {
        model.addProxy(proxy);
        BufferedImage bufImg = proxy.display();
        Image scaled = bufImg.getScaledInstance(view.getThumbnailWidth(), view.getThumbnailHeight() , Image.SCALE_SMOOTH);
        model.addThumbnail(new ImageIcon(scaled));
        JButton label = new JButton(model.getThumbnails().get(model.getThumbnails().size() - 1));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        label.addActionListener(new LoadImageAction(proxy));
        view.add(label);
        view.revalidate();
        view.repaint();
    }

    public boolean daLiSadrzi(String filePath){
        for(ImageInterface img: model.getProxies()){
            if(filePath.equals(((ProxyImage)img).getFilePath())){
                return true;
            }
        }
        return false;
    }


}
