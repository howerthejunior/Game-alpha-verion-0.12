package main;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundURL[]=new URL[30];

    public Sound(){ // Initialize soundURL array in the constructor
        soundURL[0]=getClass().getResource("/sound/BlueBoyAdventure.wav");
        soundURL[1]=getClass().getResource("/sound/coin.wav");
        soundURL[4]=getClass().getResource("/sound/fanfare.wav");
        soundURL[2]=getClass().getResource("/sound/powerup.wav");
        soundURL[3]=getClass().getResource("/sound/unlock.wav");
    }
    public void setFile(int i){
        try {
            AudioInputStream ais= AudioSystem.getAudioInputStream(soundURL[i]);
            clip =AudioSystem.getClip();
            clip.open(ais);
        }catch (IOException | UnsupportedAudioFileException e){
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
    public void play(){
    clip.start();
    }
    public void loop(){
  clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
          clip.stop();
    }
}
