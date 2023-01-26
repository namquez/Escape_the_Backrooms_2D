package main;

import entity.ShadowMan;
import object.Candle;
import object.Ladder;

// can move this all to main game panel
public class ObjectPlacer {
    GamePanel gp;

    public ObjectPlacer(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new Ladder();
        gp.obj[0].mapX = 55 * gp.tileSize;
        gp.obj[0].mapY = 34 * gp.tileSize;

        gp.obj[1] = new Candle();
        gp.obj[1].mapX = 14 * gp.tileSize;
        gp.obj[1].mapY = 14 * gp.tileSize;

    }
    public void setNPC() {
        gp.npc[0] = new ShadowMan(gp);
        gp.npc[0].mapX = gp.tileSize*16;
        gp.npc[0].mapY = gp.tileSize*22;

        gp.npc[1] = new ShadowMan(gp);
        gp.npc[1].mapX = gp.tileSize*13;
        gp.npc[1].mapY = gp.tileSize*44;

        gp.npc[2] = new ShadowMan(gp);
        gp.npc[2].mapX = gp.tileSize*40;
        gp.npc[2].mapY = gp.tileSize*37;
    }

}
