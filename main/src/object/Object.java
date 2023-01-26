package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Object {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int mapX, mapY;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(Graphics2D g2, GamePanel gp) {

        int screenX = mapX - gp.player.mapX + gp.player.screenX;
        int screenY = mapY - gp.player.mapY + gp.player.screenY;

        g2.drawImage(image, screenX, screenY , gp. tileSize, gp.tileSize, null);

    }


}
