package entity;

public class MusicPlayer {
    private String currentPlaylist;
    private boolean isPlaying;
    private int volume;
    private String currentTrack;

    public MusicPlayer() {
        this.currentPlaylist = "";
        this.isPlaying = false;
        this.volume = 50;
        this.currentTrack = "";
    }

    public String getCurrentPlaylist() {
        return currentPlaylist;
    }

    public void setCurrentPlaylist(String currentPlaylist) {
        this.currentPlaylist = currentPlaylist;
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

    public String getCurrentTrack() {
        return currentTrack;
    }

    public void setCurrentTrack(String currentTrack) {
        this.currentTrack = currentTrack;
    }

}
