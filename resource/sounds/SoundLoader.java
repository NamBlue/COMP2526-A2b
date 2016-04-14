package sounds;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Loader and player for sound resources.
 * 
 * @author Jia Qi Lee
 * @version 1.0
 */
public class SoundLoader {
    private Clip clip;
    
    public static final SoundLoader bgm = new SoundLoader("bgm.wav");
    
    /**
     * Constructor for objects of type SoundLoader.
     * 
     * @param name the URI of the sound file to load.
     */
    public SoundLoader(String name) {
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(getClass().getResource(name)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Plays the currently loaded sound.
     */
    public void play() {
        new Thread() {
            public void run() {
                synchronized (clip) {
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                    clip.start();
                }
            }
        }.start();
    }
    
    /**
     * Pauses the currently playing sound.
     */
    public void pause() {
        clip.stop();
    }
}
