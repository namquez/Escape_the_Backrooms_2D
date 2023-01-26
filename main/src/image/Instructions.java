package image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Instructions {
    public BufferedImage image;
    public String name;

    public Instructions() {
        name = "Instructions";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/instructions.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
