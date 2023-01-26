package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;


public class Player extends Entity{


    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public boolean gotCandle = false;

    public boolean lightUpdated = true;
    public int lightRadius;

    public double playTime;


    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);

        this.gp = gp;
        this.keyH = keyH;

        //centering my character
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8, 16, 32, 32); // solid part of the character
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }


    public void setDefaultValues() {

        mapX = gp.tileSize * 8;
        mapY = gp.tileSize * 6;
        speed = 4;
        direction = "down";
        lightRadius = 1;
        gotCandle = false;
        lightUpdated = true;
        playTime = 100;
    }

    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_2.png")));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if(keyH.upPressed || keyH.downPressed ||
                keyH.leftPressed || keyH.rightPressed) {

            // check direction
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.rightPressed) {
                direction = "right";
            }

            // check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // check NPC collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // player can only move if collision is off
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



            // every 10 frames, we change the sprite to create player animation
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
        //update and check timer
        playTime -= (double) 1 / 60;

        if (playTime < 0) {
            gp.gameState = gp.gameLoseState;
            gp.playSE(2);
            gp.setMusic(6);
        }

        else if (playTime < 11 && playTime > 10.99) {
            gp.playSE(3);
        }


    }

    public void pickUpObject(int i) {

        if(i != 999) {
            if (gp.obj[i] == gp.obj[0]) {
                gp.gameState = gp.gameWinState;
                gp.setMusic(6);
                gp.playSE(5);
            } else if (gp.obj[i] == gp.obj[1]) {
                gotCandle = true;
                lightUpdated = true;
                lightRadius = 300;
                gp.obj[i] = null;
                gp.playSE(4);
            }
        }
    }

    public void interactNPC(int i){
        if (i != 999) {
            gp.gameState = gp.gameLoseState;
            gp.playSE(2);
            gp.setMusic(6);
        }
    }


    public void draw(Graphics2D g2) {

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
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }


}
