package org.cis1200;

import main.GamePanel;

import javax.swing.JFrame;
import java.io.FileNotFoundException;

public class Game {

    public static void main(String[] args) throws FileNotFoundException {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Escape the Backrooms 2D");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();

    }

}
