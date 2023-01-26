package main;

import javax.swing.JPanel;
import java.awt.*;
import java.io.FileNotFoundException;

import data.SaveLoad;
import entity.Entity;
import entity.Player;
import environment.ScreenSetter;
import object.Object;
import tile.TileManager;


public class GamePanel extends JPanel implements Runnable {

    //screen settings

    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //map settings
    public final int maxMapCol = 64;
    public final int maxMapRow = 60;


    // frames per second
    int FPS = 60;

    public TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);

    Sound soundEffect = new Sound();
    Sound music = new Sound();
    SaveLoad saveLoad = new SaveLoad(this);
    ScreenSetter eManager = new ScreenSetter(this);
    Thread gameThread; //giving the game a sense of time!
    public CollisionChecker cChecker = new CollisionChecker(this);
    public ObjectPlacer oPlacer = new ObjectPlacer(this);
    public UI ui = new UI(this);
    public Player player = new Player(this, keyH);
    public Object obj[] = new Object[2];
    public Entity npc[] = new Entity[10];

    // game state
    public int gameState;
    public final int titleState = 0;
    public final int instructionState = 1;
    public final int playState = 2;
    public final int pauseState = 3;
    public final int gameWinState = 4;
    public final int gameLoseState = 5;






    public GamePanel() throws FileNotFoundException {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);// this allows GamePanel to be "focused" to recieve input
    }


    public void setupGame() {
        oPlacer.setObject();
        oPlacer.setNPC();
        gameState = titleState;
        eManager.setup();
        setMusic(0);
    }

    public void restart() {
        player.setDefaultValues();
        oPlacer.setObject();

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    //override jpanel
    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; //nanoseconds divided by fps, drawing screen 60 times per sec
        double nextDrawTime = System.nanoTime() + drawInterval;



        while (gameThread != null) {
            // 1: update info such as character position
            update();
            // 2: draw the screen with the updated information (calls paintcomponent method)
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000; // convert to miliseconds to use sleep method

                if(remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime); // tells the game to stop updating, until next interval

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void update() {

        if(gameState == playState) {
            player.update();
            eManager.update();
            for (Entity entity : npc) {
                if (entity != null) {
                    entity.update();
                }
            }
        }

    }
    public void paintComponent(Graphics g){ // this function gets called by repaint()

        //super is the parent class of this class, JPanel
        super.paintComponent(g);

        // Graphics 2D extends the Graphic Class, and gives us more control over shapes and layout
        Graphics2D g2 = (Graphics2D)g;

        // tile screen
        if(gameState == titleState) {
            ui.draw(g2);
        } else {
            // tiles
            tileM.draw(g2);
            //objects
            for (Object object : obj) {
                if (object != null) {
                    object.draw(g2, this);
                }
            }
            // npc
            for (Entity entity : npc) {
                if (entity != null) {
                    entity.draw(g2);
                }
            }

            // player
            player.draw(g2);

            //environment
            eManager.draw(g2);

            // ui
            ui.draw(g2);
        }

        g2.dispose(); // get rid of this gctx
    }
    public void setMusic(int i) {
        music.stop();
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic() {
        music.stop();
    }

    public void stopSounds() {
        soundEffect.stop();
    }
    public void playSE(int i) {
        soundEffect.setFile(i);
        soundEffect.play();
    }
}
