package assets;

import javax.sound.sampled.*;
import java.net.URL;
import java.util.HashMap;

public class Sound {
    public static final String KEY_OF_JUMP_SOUND = "jump";
    public static final String KEY_OF_LOSE_SOUND = "lose";
    public static final String KEY_OF_BACKGROUND_SOUND = "background";

    private final HashMap<String, Clip> soundContainerHash = new HashMap<>();

    public Sound() {
        loadClip(KEY_OF_JUMP_SOUND, "/sound/jump.wav");
        loadClip(KEY_OF_LOSE_SOUND, "/sound/lose.wav");
        loadClip(KEY_OF_BACKGROUND_SOUND, "/sound/background_sound.wav");
    }

    private void loadClip(String keyOfSound, String path) {
        try {
            URL soundURL = getClass().getResource(path);
            AudioInputStream audio = AudioSystem.getAudioInputStream(soundURL);
            Clip soundValueForBucket = AudioSystem.getClip();
            soundValueForBucket.open(audio);
            soundContainerHash.put(keyOfSound, soundValueForBucket);
        } catch (Exception e) {
            System.err.println("ERROR loading sound: " + keyOfSound);
        }
    }

    private void playSongOnce(String keyOfSound) {
        Clip sound = soundContainerHash.get(keyOfSound);
        if (sound != null) {
            sound.stop();
            sound.setFramePosition(0);
            sound.start();
        }
    }

    private void loopOfSound(String keyOfSound) {
        Clip sound = soundContainerHash.get(keyOfSound);
        if (sound != null) {
            sound.loop(Clip.LOOP_CONTINUOUSLY);
            sound.start();
        }
    }

    private void stopSound(String keyOfSound) {
        Clip sound = soundContainerHash.get(keyOfSound);
        if (sound != null) {
            sound.stop();
        }
    }

    public void playJumpSound() {
        this.playSongOnce(KEY_OF_JUMP_SOUND);
    }

    public void playLoseSound() {
        this.playSongOnce(KEY_OF_LOSE_SOUND);
    }

    public void stopLoseSound() {
        this.stopSound(KEY_OF_LOSE_SOUND);
    }

    public void playBackgroundSound() {
        this.loopOfSound(KEY_OF_BACKGROUND_SOUND);
    }

    public void stopBackgroundSound() {
        this.stopSound(KEY_OF_BACKGROUND_SOUND);
    }
}