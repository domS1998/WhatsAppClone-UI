package org.main.sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.net.URISyntaxException;


public class SoundPlayer {

    private String musicFile = null;
    private Media sound = null;
    private MediaPlayer mediaPlayer = null;
    private MediaView mediaView = null;
    private Thread BgThread = null;
    private static SoundPlayer uniqueInstance;

    private SoundPlayer() {}

    public static SoundPlayer getInstance(){

        if (SoundPlayer.uniqueInstance == null) {
            SoundPlayer.uniqueInstance = new SoundPlayer();
            return SoundPlayer.uniqueInstance;
        }
        return SoundPlayer.uniqueInstance;
    }

    public void play(String name) {

        try {
            sound = new Media(getClass().getResource(getFilePath(name)).toURI().toString());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(0.05);
        mediaPlayer.play();

    }

    String getFilePath(String name){
        return switch(name){
            case "send"            : yield "/sound/submit-plop.wav";
            case "receive"         : yield "/sound/iphone-received-message-1.wav";
            case "no-connection"   : yield "/sound/no-connection.wav";
            default                : yield "default";
        };
    }

    public void mute(boolean val){
        this.getInstance().mediaView.getMediaPlayer().setMute(val);
    }




}

