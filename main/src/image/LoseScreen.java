package image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class LoseScreen {
    public BufferedImage image;
    public String name;

    public LoseScreen() {
        name = "Lose Screen";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/lose.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
