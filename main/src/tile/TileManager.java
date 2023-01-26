package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum; // instantiating our 2D Array for our map

    public TileManager(GamePanel gp) {
        this.gp = gp;

        //create list of the different tiles
        tile = new Tile[3];

        //setting max rows and cols
        mapTileNum = new int[gp.maxMapCol][gp.maxMapRow];

        getTileImage();
        loadMap("/maps/map2.txt");

    }

    public void getTileImage() {

        try {
            // set floor image and collision
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/floor.png")));
            tile[0].collision = false;

            // set wall image and collision
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));
            tile[1].collision = true;

            // set wall image and collision
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/poolroom_water.png")));
            tile[2].collision = true;



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            // reading the text file
            InputStream is = getClass().getResourceAsStream(filePath); //imports text file
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); //read content of text file

            int col = 0;
            int row = 0;

            //continue loop until everything in numbers[] is stored in the mapTileNum
            while (col < gp.maxMapCol && row < gp.maxMapRow) {
                String line = br.readLine();
                while (col < gp.maxMapCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]); //change from string to int

                    mapTileNum[col][row] = num;
                    col++;

                }
                if (col == gp.maxMapCol) {
                    col = 0;
                    row++;
                }

            }
            br.close();

        } catch (Exception e) {

        }
    }


    public void draw(Graphics2D g2) {

        int mapCol = 0;
        int mapRow = 0;

        while (mapCol < gp.maxMapCol && mapRow < gp.maxMapRow) {

            int tileNum = mapTileNum[mapCol][mapRow];

            //adjusting screen position based on player
            int mapX = mapCol * gp.tileSize;
            int mapY = mapRow * gp.tileSize;
            int screenX = mapX - gp.player.mapX + gp.player.screenX;
            int screenY = mapY - gp.player.mapY + gp.player.screenY;

            g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            mapCol++;

            if (mapCol == gp.maxMapCol) {
                mapCol = 0;
                mapRow++;
            }
        }

    }
}
