package entity;

import constants.Constants;

/**
 * Represents a MusicPlayer with a current playlist, playing state, volume, and current track.
 */
public class MusicPlayer {
    private boolean isPlaying;
    private int volume;

    /**
     * Creates a new MusicPlayer with default values.
     */
    public MusicPlayer() {
        this.isPlaying = false;
        this.volume = Constants.DEFAULT_VOLUME;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

}
