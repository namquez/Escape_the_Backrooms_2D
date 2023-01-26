package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); // gets integer keycode associated w key pressed

        //title state
        if (gp.gameState == gp.titleState) {
            if (code == KeyEvent.VK_N) {
                gp.gameState = gp.playState;
                gp.restart();
                gp.setMusic(1);
                gp.playSE(7);
                gp.playSE(8);

            }
            if (code == KeyEvent.VK_L) {
                gp.saveLoad.load();
                gp.gameState = gp.playState;
                gp.playSE(8);
                gp.setMusic(1);
            }
            if (code == KeyEvent.VK_ESCAPE) {
                gp.playSE(8);
                System.exit(0);
                }
            if (code == KeyEvent.VK_I) {
                gp.gameState = gp.instructionState;
                gp.playSE(8);
            }
        }

        // instruction state
        if(gp.gameState == gp.instructionState) {
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.titleState;
                gp.playSE(8);
            }
        }


        // pause state
        if(gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
                gp.playSE(8);
            }
            if (code == KeyEvent.VK_ESCAPE) {
                gp.saveLoad.save();
                gp.gameState = gp.titleState;
                gp.stopSounds();
                gp.playSE(8);
                gp.setMusic(0);
            }
        }

        // play state
        if(gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_UP) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_DOWN) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.pauseState;
                gp.playSE(8);
            }

        }

        // win state
        if(gp.gameState == gp.gameWinState) {
            if (code == KeyEvent.VK_ESCAPE) {
                gp.saveLoad.save();
                gp.gameState = gp.titleState;
                gp.playSE(8);
                gp.setMusic(0);
            }
        }

            // lose state
            if(gp.gameState == gp.gameLoseState) {
                if (code == KeyEvent.VK_ESCAPE) {
                    gp.saveLoad.save();
                    gp.gameState = gp.titleState;
                    gp.playSE(8);
                    gp.setMusic(0);
                }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }

    }
}
