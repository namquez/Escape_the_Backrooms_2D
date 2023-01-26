package environment;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Lighting {

    GamePanel gp;
    BufferedImage darknessFilter;

    public Lighting(GamePanel gp) {
        this.gp = gp;
        setLightSource();

    }
    public void setLightSource() {
        // create a buffered image
        darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D)darknessFilter.getGraphics();

        if(gp.player.gotCandle == false) {
            g2.setColor(new Color(0,0,0,0.9f));
        } else {
            // get the center of the light circle
            int centerX = gp.player.screenX + (gp.tileSize)/2;
            int centerY = gp.player.screenY + (gp.tileSize)/2;

            //circle gradient
            Color color[] = new Color[5];
            float fraction[] = new float[5];

            color[0] = new Color(0,0,0,0f);
            color[1] = new Color(0,0,0,0.25f);
            color[2] = new Color(0,0,0,0.5f);
            color[3] = new Color(0,0,0,0.75f);
            color[4] = new Color(0,0,0,0.9f);

            fraction[0] = 0f;
            fraction[1] = 0.25f;
            fraction[2] = 0.5f;
            fraction[3] = 0.75f;
            fraction[4] = 1f;

            //gradient settings
            RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, gp.player.lightRadius, fraction, color);
            g2.setPaint(gPaint);
        }

        g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);

        g2.dispose();

    }
    public void update() {
        if (gp.player.lightUpdated) {
            setLightSource();
            gp.player.lightUpdated = false;
        }

    }

    public void draw(Graphics2D g2) {
        g2.drawImage(darknessFilter, 0, 0, null);
    }

}
