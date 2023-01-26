package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.*;

public class ShadowMan extends Entity {

    public ShadowMan(GamePanel gp) {

        super(gp);

        direction = "down";
        speed = 3;

        getImage();
    }
    public void getImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/shadow_man/shadow_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/shadow_man/shadow_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/shadow_man/shadow_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/shadow_man/shadow_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/shadow_man/shadow_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/shadow_man/shadow_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/shadow_man/shadow_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/shadow_man/shadow_right_2.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public void setAction() {

            if (Math.abs(gp.player.mapX - this.mapX) < gp.tileSize * 5 && Math.abs(gp.player.mapY - this.mapY) < gp.tileSize * 5) {
                direction = BFSRecursive(this.mapX, this.mapY,gp.player.mapX, gp.player.mapY);
                if (direction != "") {
                    return;
                }
            }

                Random random = new Random();
                int i = random.nextInt(5);

                if(i == 1) {
                    direction = "up";
                }
                if(i == 2) {
                    direction = "down";
                }
                if(i == 3) {
                    direction = "left";
                }
                if(i == 4) {
                    direction = "right";
                }
            }

    private String BFSRecursive(int startX, int startY, int endX, int endY) {
        int startRow = startX/ gp.tileSize;
        int startCol = startY/ gp.tileSize;
        int endRow = endX/ gp.tileSize;
        int endCol = endY/gp.tileSize;
        int[][] map = gp.tileM.mapTileNum;
        int maxRows = map.length;
        int maxColumns = map[0].length;

        ArrayList<int[]> frontier = new ArrayList<int[]>();

        //initializing our frontier
        int[] startUp = {startRow + 1, startCol, 1}; //right
        frontier.add(startUp);
        int[] startRight = {startRow, startCol - 1, 2}; //up
        frontier.add(startRight);
        int[] startDown = {startRow - 1, startCol, 3}; //left
        frontier.add(startDown);
        int[] startLeft = {startRow, startCol + 1, 4}; //down
        frontier.add(startLeft);

        Set<String> visited = new HashSet<String>();

        int res = BFSHelper(frontier,endRow,endCol,maxRows, maxColumns,visited,map);

        if (res == 1){
            return "right";
        } else if (res == 2){
            return "up";
        } else if (res == 3){
            return "left";
        } else if (res == 4){
            return "down";
        } else{
            return "no_sol";
        }
    }

    private int BFSHelper(ArrayList<int[]> frontier, int goalR, int goalC, int maxRows, int maxColumns, Set<String> visited,int[][] map){
        if (frontier.size() == 0){
            return 0;
        }

        ArrayList<int[]> newFrontier = new ArrayList<int[]>();
        for (int j = 0; j < frontier.size(); j++){
            int [] curr = frontier.get(j);
            int currRow = curr[0];
            int currCol = curr[1];
            int startDirection = curr[2];
            String s = Integer.toString(currRow) + " " + (Integer.toString(currCol));

            if (currRow == goalR && currCol == goalC){
                return startDirection;
            }

            if (currRow < 0 || currRow >= maxRows
                    || currCol < 0 || currCol >= maxColumns || map[currRow][currCol] !=0 || visited.contains(s)) {
                continue;
            }

            int[] newUp = {currRow - 1, currCol, startDirection};
            newFrontier.add(newUp);
            int[] newRight = {currRow, currCol + 1, startDirection};
            newFrontier.add(newRight);
            int[] newDown = {currRow + 1, currCol, startDirection};
            newFrontier.add(newDown);
            int[] newLeft = {currRow, currCol - 1, startDirection};
            newFrontier.add(newLeft);
            visited.add(s);

        }

        return BFSHelper(newFrontier, goalR, goalC, maxRows, maxColumns,visited,map);
    }
}
