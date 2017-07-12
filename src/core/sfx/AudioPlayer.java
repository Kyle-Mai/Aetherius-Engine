package core.sfx;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import sun.misc.JavaNioAccess;
import sun.nio.ch.DirectBuffer;
import sun.nio.ch.Interruptible;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleTable;
import java.io.File;
import java.io.Flushable;
import java.util.*;
import java.util.function.BooleanSupplier;

/*
Lolita's Revenge
June 29 2017

Plays and manages audio files.
 */

public class AudioPlayer extends ArrayList<Media> implements Runnable {

    /*------------------------------------------------------------------------------------------------------------------
     Variables.
     Define the function of the SFX player.
     */

    private final long sleepDuration = 600; //How long the thread's refresh rate is while audio is playing

    private double delay = 0;
    private boolean loop = false;
    private boolean playing = false;
    private boolean shuffle = false;
    private MediaPlayer mediaPlayer;
    private ArrayList<File> audioFile = new ArrayList<>();
    static { JFXPanel fxPanel = new JFXPanel(); } //needed to play the audio

    /*------------------------------------------------------------------------------------------------------------------
     Constructors.
     Used to construct instances of the SFX player.
     */

    public AudioPlayer() {}

    public AudioPlayer(File a) { audioFile.add(a); }

    public AudioPlayer(File a, double v) {
        audioFile.add(a);
        setVolume(v);
    }

    public AudioPlayer(File a, double v, double d) {
        audioFile.add(a);
        setVolume(v);
        delay = d;
    }

    /*------------------------------------------------------------------------------------------------------------------
     Accessible methods.
     Can be accessed outside of the SFX player to edit values.
     */

    public void addFile(File... file) { audioFile.addAll(Arrays.asList(file)); } //adds a new audio file to the sfx player
    public void removeFile(File f) { audioFile.remove(f); } //removes a file
    public void removeFile(int i) { audioFile.remove(i); } //removes a file from the selected index
    public void dumpFiles() { audioFile.clear(); }

    public File getFile(File f) { return audioFile.get(audioFile.indexOf(f)); } //gets a file
    public File getFile(int i) { return audioFile.get(i); } //gets a file from an index
    public int getFileIndex(File f) { return audioFile.indexOf(f); } //gets the index of a file

    public void setVolume(double volume) {mediaPlayer.setVolume(0.01 * volume); } //sets the volume of the audio player
    public void setDelay(double d) { delay = d; } //sets the delay between initialization and playing

    public double getVolume() { return mediaPlayer.getVolume() * 100; } //gets the volume of the audio player
    public double getDuration() { return mediaPlayer.getTotalDuration().toMillis(); } //gets the duration of the current audio
    public double getDelay() { return delay; } //gets the currently selected delay

    public void loop() { //loops the audio
        audioLoop();
        loop = true;
    }

    public boolean isPlaying() { return playing; }
    public boolean isLooping() { return loop; } //whether or not the audio is looping
    public void setShuffle(boolean b) { shuffle = b; } //shuffles the audio
    public boolean isShuffling() { return shuffle; } //whether or not the audio is being shuffled

    public void stopAudio() { mediaPlayer.stop(); } //stops the audio

    @Override
    public void run() { //plays the audio
        if (playing) throw new RuntimeException("Invalid operation - Audio thread was accessed during execution."); //Safety net.

        initializeData();
        mediaPlayerSetup();

        if (loop && !shuffle && size() <= 1) { audioLoop(); }

        if (size() == 1) {
            loadAudio(get(0));
        } else if (size() > 1 && shuffle) {
            shuffleAudio();
        } else if (size() > 1 && !shuffle && !loop) {
            playList();
        } else {
            throw new IllegalArgumentException("Unexpected Error - Audio initialization was incorrect.");
        }

        Thread.currentThread().interrupt(); //closes the thread down
    }

    /*------------------------------------------------------------------------------------------------------------------
     Core methods.
     Methods used by the run function when the audio is set to play. These should NOT be touched or accessed.
     */

    private void mediaPlayerSetup() {
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                System.out.println("Audio ended.");
                playing = false;
                Thread.currentThread().interrupt();
            }
        });
    }

    private void audioLoop() { mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); }

    private void initializeData() { //initializes the audio files from the data
        clear();
        if (audioFile.size() == 0) {
            throw new NullPointerException("Unexpected Error - No audio loaded.");
        } else {
            for (int i = 0; i < audioFile.size(); i++) {
                add(new Media(audioFile.get(i).toString()));
            }
        }
        audioFile.clear(); //All audio files initialized, might as well dump the now useless data
    }

    private void loadAudio(Media media) { //loads the audio into the media player and plays it after the delay (if one was set)
        mediaPlayer = new MediaPlayer(media);
        try {
            Thread.sleep((long)delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        mediaPlayer.play();
        playing = true;

        while(playing) {
            try {
                Thread.currentThread().sleep(sleepDuration);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    private void shuffleAudio() { //selects a random audio from the list and plays it, looping when applicable
        Random shuffler = new Random();
        loadAudio(get(shuffler.nextInt(size() - 1)));
        if (loop) { //if the program is set to loop, choose another audio file after the current one finishes playing
            shuffleAudio();
        }
    }

    private void playList() { //plays the audio in order
        for (int i = 0; i < size(); i++) {
            loadAudio(get(i));
        }
    }

    //------------------------------------------------------------------------------------------------------------------

}
