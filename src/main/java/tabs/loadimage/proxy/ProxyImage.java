package tabs.loadimage.proxy;

import lombok.Getter;

import java.awt.image.BufferedImage;

@Getter
public class ProxyImage implements ImageInterface {

    HighDefinitionImage realImage;
    String filePath;

    public ProxyImage(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public BufferedImage display() {
        if(realImage == null){
            realImage = new HighDefinitionImage(filePath);
        }
        return realImage.getImg();
    }
}
