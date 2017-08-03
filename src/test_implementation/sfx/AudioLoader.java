package test_implementation.sfx;

import core.sfx.AudioPlayer;

import java.io.File;

/**
 * Lolita's Revenge
 * July 30 2017
 * Loads audio into the audio player.
 */

public class AudioLoader {

    private AudioPlayer audioMain; //creates an instance of the audio player class

    public AudioLoader() {
        audioMain = new AudioPlayer();
        audioMain.setVolume(3); //sets the volume to 3 (computer is stupidly loud lol)
    }

    //this test demonstrates setting and loading audio from a designated folder used as a resource directory
    public void loadAudioTestOne() {
        audioMain.setAudioFolder(new File(System.getProperty("user.dir") + "/src/test_implementation/sfx/resources/")); //sets the audio directory to the resources folder
        audioMain.addAudioFromFolder("clownpiece.mp3"); //adds the file 'clownpiece.mp3' from the audio directory folder declared earlier via setAudioFolder
        audioMain.setCloseOnComplete(true); //this setting will automatically kill the audio thread once it has finished playing all audio
    }

    //this test demonstrates loading audio through a URL rather than a folder, this has the benefit of allowing audio calls from multiple locations pretty easily
    public void loadAudioTestTwo() {
        audioMain.addFile(getClass().getResource("resources/clownpiece.mp3")); //gets the resource from the class, note that it cannot have a slash in front of resources or it won't work, blame java
        audioMain.setCloseOnComplete(true);
    }

    //opens up the audio thread and plays the audio
    public void playAudio() {
        new Thread(audioMain).start(); //opens up a new thread and plays the audio
    }

    //this test demonstrates killing an audio thread during playing by using the dispose() method
    //kills the audio thread and closes all of the processes to ensure it dies, audio player must be re-initialized completely
    public void interruptAudioTestOne() {
        audioMain.dispose();
    }

    //this test demonstrates killing an audio thread during playing by stopping the audio
    //thread is left open and active
    public void interruptAudioTestTwo() {
        audioMain.stop();
    }

    //this test demonstrates killing an audio thread by accessing the audio player's thread and interrupting it from there
    //kills the audio thread, audio thread's information is left intact and it can easily be restarted using start() again
    public void interruptAudioTestThree() {
        audioMain.getAudioThread().interrupt();
        System.out.println("Audio thread interrupted!");
    }

}
