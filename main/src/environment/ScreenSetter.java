package environment;

import main.GamePanel;

import java.awt.*;

public class ScreenSetter {

    GamePanel gp;
    Lighting lighting;

    public ScreenSetter(GamePanel gp) {
        this.gp = gp;

    }
    public void setup() {

        lighting = new Lighting(gp);

    }
    public void update(){
        lighting.update();
    }
    public void draw(Graphics2D g2) {
        lighting.draw(g2);
    }
}
