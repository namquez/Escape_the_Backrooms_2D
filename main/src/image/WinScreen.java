package image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class WinScreen {
    public BufferedImage image;
    public String name;

    public WinScreen() {
        name = "Win Screen";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/win.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
