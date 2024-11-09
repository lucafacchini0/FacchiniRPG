package com.lucafacchini;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    public final String SOUND_PATH = "/sounds/";

    Clip clip;
    URL soundURL[] = new URL[30]; //

    public Sound() {
        loadSound();
    }

    private void loadSound() {
        soundURL[0] = getClass().getResource(SOUND_PATH + "merchant.wav");
        soundURL[1] = getClass().getResource(SOUND_PATH + "coin.wav");
        soundURL[2] = getClass().getResource(SOUND_PATH + "powerup.wav");
        soundURL[3] = getClass().getResource(SOUND_PATH + "unlock.wav");
        soundURL[4] = getClass().getResource(SOUND_PATH + "fanfare.wav");
    }

    public void setFile(int index) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[index]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}