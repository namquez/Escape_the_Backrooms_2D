package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Ladder extends Object {
    public Ladder() {
        name = "Ladder";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/ladder.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
