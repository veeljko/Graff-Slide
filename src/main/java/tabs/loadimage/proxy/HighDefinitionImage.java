package tabs.loadimage.proxy;

import lombok.Getter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Getter
public class HighDefinitionImage implements ImageInterface {

    String filePath;
    BufferedImage img;

    public HighDefinitionImage(String filePath) {
        this.filePath = filePath;
        loadImage(filePath);
    }

    private void loadImage(String filePath){
        try {
            File f = new File(filePath);
            img = ImageIO.read(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BufferedImage display() {
        return img;
    }
}
