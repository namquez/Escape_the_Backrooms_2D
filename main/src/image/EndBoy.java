package image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class EndBoy {
    String name;
    public BufferedImage image;
    public EndBoy() {
        name = "End Boy";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/end_boy.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}