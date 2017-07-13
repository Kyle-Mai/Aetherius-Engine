package core.sfx;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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
    private boolean paused = false;
    private boolean shuffle = false;
    private boolean closeOnComplete = true;
    private MediaPlayer mediaPlayer;
    private double volume = 100;
    private ArrayList<URL> audioFile = new ArrayList<>();

    private Runnable onComplete;

    static { JFXPanel fxPanel = new JFXPanel(); } //needed to play the audio

    /*------------------------------------------------------------------------------------------------------------------
     Constructors.
     Used to construct instances of the SFX player.
     */

    public AudioPlayer() {}

    public AudioPlayer(URL a) { audioFile.add(a); }

    public AudioPlayer(URL a, double v) {
        audioFile.add(a);
        setVolume(v);
    }

    public AudioPlayer(URL a, double v, double d) {
        audioFile.add(a);
        setVolume(v);
        delay = d;
    }

    /*------------------------------------------------------------------------------------------------------------------
     Accessible methods.
     Can be accessed outside of the SFX player to edit values.
     */

    public void addFile(URL... file) { audioFile.addAll(Arrays.asList(file)); } //adds a new audio file to the sfx player
    public void removeFile(URL f) { audioFile.remove(f); } //removes a file
    public void removeFile(int i) { audioFile.remove(i); } //removes a file from the selected index
    public void dumpFiles() { audioFile.clear(); }

    public URL getFile(URL f) { return audioFile.get(audioFile.indexOf(f)); } //gets a file
    public URL getFile(int i) { return audioFile.get(i); } //gets a file from an index
    public int getFileIndex(URL f) { return audioFile.indexOf(f); } //gets the index of a file

    public void setShuffle(boolean b) { shuffle = b; } //shuffles the audio

    public void setVolume(double vol) { //sets the volume of the audio player
        if (vol < 0) {
            volume = 0;
        } else if (vol > 100) {
            volume = 100;
        } else {
            volume = vol;
        }

        if (mediaPlayer != null) { //only attempt to alter the media player if one is initialized
            mediaPlayer.setVolume(0.01 * volume);
        }
    }

    public void setDelay(double d) { delay = d; } //sets the delay between initialization and playing

    public double getVolume() { return mediaPlayer.getVolume() * 100; } //gets the volume of the audio player
    public double getDuration() { return mediaPlayer.getTotalDuration().toMillis(); } //gets the duration of the current audio
    public double getDelay() { return delay; } //gets the currently selected delay

    public void pause() { //pauses the currently playing audio
        if (playing) {
            mediaPlayer.pause();
            paused = true;
            playing = false;
        }
    }

    public void resume() { //resumes the currently playing audio
        if (paused) {
            mediaPlayer.play();
            playing = true;
            paused = false;
        }
    }

    public void stop() { mediaPlayer.stop(); } //stops the audio

    public void loop() { //loops the audio
        loop = true;
    }

    public void toggleMute() { mediaPlayer.setMute(!mediaPlayer.isMute()); }

    public boolean isPlaying() { return playing; } //whether or not there's any audio playing
    public boolean isLooping() { return loop; } //whether or not the audio is looping
    public boolean isPaused() { return paused; } //whether or not the audio is paused
    public boolean isShuffling() { return shuffle; } //whether or not the audio is being shuffled
    public boolean isMuted() { return mediaPlayer.isMute(); }

    public void setCloseOnComplete(boolean b) { closeOnComplete = b; } //whether or not the audio thread will clean itself up on closing
    public boolean isCloseOnComplete() { return closeOnComplete; }

    public void setOnComplete(Runnable r) { onComplete = r; }
    public Runnable getOnComplete() { return onComplete; }

    @Override
    public void run() { //plays the audio
        if (playing) throw new RuntimeException("Invalid operation - Audio thread was accessed during execution."); //Safety net.

        initializeData();

        if (size() == 1) {
            loadAudio(get(0));
        } else if (size() > 1 && shuffle) {
            shuffleAudio();
        } else if (size() > 1 && !shuffle && !loop) {
            playList();
        } else {
            throw new IllegalArgumentException("Unexpected Error - Audio initialization was incorrect.");
        }

        if (onComplete != null) { onComplete.run(); } //if a runnable was set to trigger on the completion of the audio player's queue, run it

        if (closeOnComplete) {
            mediaPlayer.dispose();
            clear();
            audioFile.clear();
            Thread.currentThread().interrupt(); //closes the thread down
        }
    }

    /*------------------------------------------------------------------------------------------------------------------
     Core methods.
     Methods used by the run function when the audio is set to play. These should NOT be touched or accessed.
     */

    private void mediaPlayerSetup() {
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                System.out.println("Audio finished playing.");
                playing = false;
                paused = false;
                if (!loop && !shuffle) {
                    Thread.currentThread().interrupt();
                }
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
        mediaPlayerSetup();

        if (delay > 0) { //delay inputted, pause thread before playing audio
            try {
                Thread.sleep((long)delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } else if (delay < 0) { //negative delay inputted -- is this a mistake?
            throw new IllegalArgumentException("Delay cannot be a negative number.");
        }

        if (loop && !shuffle && size() <= 1) { audioLoop(); }

        mediaPlayer.setVolume(0.01 * volume);
        mediaPlayer.play();
        playing = true;

        while(playing || paused) { //Pause until the audio finishes playing
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
