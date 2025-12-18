package tabs.loadimage.model;

import lombok.Getter;
import tabs.loadimage.LoadImageView;
import tabs.loadimage.proxy.ImageInterface;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class LoadImageModel {

    private List<ImageInterface> proxies;
    private List<ImageIcon> thumbnails;

    public LoadImageModel() {
        proxies = new ArrayList<>();
        thumbnails = new ArrayList<>();
    }

    public void addProxy(ImageInterface proxy){
        if(!proxies.contains(proxy)){
            proxies.add(proxy);
        }
    }

    public void addThumbnail(ImageIcon img){
        thumbnails.add(img);
    }



}
