package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Candle extends Object {
    public Candle() {
        name = "Candle";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/candle.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = false;
    }
}
