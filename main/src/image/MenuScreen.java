package image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class MenuScreen {
    public BufferedImage image;
    public String name;

    public MenuScreen() {
        name = "Menu Screen";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/menu.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
