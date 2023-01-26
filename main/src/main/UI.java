package main;

import image.*;
import object.Ladder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import static java.awt.Color.white;

public class UI {

    GamePanel gp;
    Font arial_40;

    DecimalFormat dFormat = new DecimalFormat("0");

    BufferedImage menuImage;
    BufferedImage winImage;
    BufferedImage loseImage;
    BufferedImage instructionImage;
    BufferedImage ladderImage;
    BufferedImage playerImage;
    private Graphics2D g2;


    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Monospaced", Font.BOLD, 20);

        Ladder ladder = new Ladder();
        ladderImage = ladder.image;
        EndBoy endboy = new EndBoy();
        playerImage = endboy.image;
        MenuScreen menu = new MenuScreen();
        menuImage = menu.image;
        Instructions instructions = new Instructions();
        instructionImage = instructions.image;
        WinScreen win = new WinScreen();
        winImage = win.image;
        LoseScreen lose = new LoseScreen();
        loseImage = lose.image;

    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        //title mode
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        // instruction mode
        if (gp.gameState == gp.instructionState) {
            drawInstructionScreen();
        }
        //play mode
        if (gp.gameState == gp.playState) {
            drawPlayScreen();
        }
        //pause mode
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
        // win mode
        if (gp.gameState == gp.gameWinState) {
            drawWinScreen();
        }
        // lose mode
        if (gp.gameState == gp.gameLoseState) {
            drawLoseScreen();
        }

    }

    public void drawTitleScreen() {
        // display menu image
        g2.drawImage(menuImage, 0, 60, gp.screenWidth, gp.screenHeight-120, null);
    }

    public void drawPlayScreen() {
        // time
        g2.drawString(dFormat.format(gp.player.playTime), gp.screenWidth / 2 - 17, 500);
    }
    public void drawInstructionScreen() {
        g2.drawImage(instructionImage, 0, 0, gp.screenWidth, gp.screenHeight, null);
    }
    public void drawPauseScreen() {
        g2.setColor(new Color(0, 0, 0, 200));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setColor(white);

        String text = "PAUSED";
        g2.drawString(text, 350, 300);

        text = "Continue (Enter)";
        g2.drawString(text, 290, 470);

        text = "Save and Exit (Esc)";
        g2.drawString(text, 273, 500);
    }

    public void drawWinScreen() {
        g2.drawImage(winImage, 0, 0, gp.screenWidth, gp.screenHeight, null);
    }

    public void drawLoseScreen() {
        g2.drawImage(loseImage, 0, 0, gp.screenWidth, gp.screenHeight, null);
    }

}


