package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL[] soundURL = new URL[30];

    public Sound() {
        //music
        soundURL[0] = getClass().getResource("/sound/menu_music.wav");
        soundURL[1] = getClass().getResource("/sound/play.wav");

        //sound effects
        soundURL[2] = getClass().getResource("/sound/death.wav");
        soundURL[3] = getClass().getResource("/sound/heartbeat.wav");
        soundURL[4] = getClass().getResource("/sound/item.wav");
        soundURL[5] = getClass().getResource("/sound/yay.wav");
        soundURL[6] = getClass().getResource("/sound/clock.wav");
        soundURL[7] = getClass().getResource("/sound/glitch.wav");
        soundURL[8] = getClass().getResource("/sound/key.wav");

    }

    public void setFile(int i) {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch(Exception e) {

        }
    }

    public void play() {
        clip.start();
    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
        try{
        clip.stop();
        } catch(NullPointerException e) {

        }
    }
}
