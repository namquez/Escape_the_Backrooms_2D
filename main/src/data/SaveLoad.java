package data;

import main.GamePanel;

import java.io.*;

public class SaveLoad {

    GamePanel gp;

    public SaveLoad(GamePanel gp) {
        this.gp = gp;
    }

    public void save() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(new File("game_file.txt"))
            );

            DataStorage ds = new DataStorage();

            ds.playTime = gp.player.playTime;
            ds.gotCandle = gp.player.gotCandle;
            ds.lightRadius = gp.player.lightRadius;
            ds.lightUpdated = true;
            ds.mapX = gp.player.mapX;
            ds.mapY = gp.player.mapY;

            // write the DataStorage object onto file
            oos.writeObject(ds);

        } catch (Exception e) {
            System.out.println("Save Exception");
        }
    }

    public void load() {

        try {
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(new File("game_file.txt"))
            );

            // read the DataStorage object
            DataStorage ds = (DataStorage) ois.readObject();

            gp.player.playTime = ds.playTime;
            gp.player.gotCandle = ds.gotCandle;
            gp.player.lightRadius = ds.lightRadius;
            gp.player.lightUpdated = ds.lightUpdated;
            gp.player.mapX = ds.mapX;
            gp.player.mapY = ds.mapY;

        } catch (Exception e) {
            System.out.println("Load Exception");
        }
    }
}
