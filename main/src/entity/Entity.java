package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    GamePanel gp;
    public int mapX, mapY;
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; // stores image files
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 24, 24); //lets us create a solid box for the character
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {}
    public void update() {

        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkPlayer(this);

        // entity can only move if collision is off
        if(!collisionOn) {
            if (direction == "up") {
                mapY -= speed;
            } else if (direction == "left") {
                mapX -= speed;
            } else if (direction == "down") {
                mapY += speed;
            } else if (direction == "right") {
                mapX += speed;
            }

        }

        // every 10 frames, we change the sprite to create entity animation
        spriteCounter++;
        if (spriteCounter > 10) {

            if (spriteNum == 1) {

                spriteNum = 2;

            } else if (spriteNum == 2) {

                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        int screenX = mapX - gp.player.mapX + gp.player.screenX;
        int screenY = mapY - gp.player.mapY + gp.player.screenY;

        BufferedImage image = null;

        if (direction == "up") {
            if (spriteNum ==1) {
                image = up1;
            }
            if (spriteNum ==2) {
                image = up2;
            }
        } else if (direction == "down") {
            if (spriteNum ==1) {
                image = down1;
            }
            if (spriteNum ==2) {
                image = down2;
            }
        } else if (direction == "left") {
            if (spriteNum ==1) {
                image = left1;
            }
            if (spriteNum ==2) {
                image = left2;
            }
        } else if (direction == "right") {
            if (spriteNum ==1) {
                image = right1;
            }
            if (spriteNum ==2) {
                image = right2;
            }
        }

        g2.drawImage(image, screenX, screenY , gp. tileSize, gp.tileSize, null);

    }

}
