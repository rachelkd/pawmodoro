package interface_adapter.music_control;

import use_case.music_control.MusicControlInputBoundary;

public class MusicControlController {
    private final MusicControlInputBoundary musicInteractor;
    
    public MusicControlController(MusicControlInputBoundary musicInteractor) {
        this.musicInteractor = musicInteractor;
    }

    public void executeTogglePlayback() {
        musicInteractor.togglePlayback();
    }

    public void togglePlayback() {
        musicInteractor.togglePlayback();
    }

    public void setVolume(int volume) {
        musicInteractor.setVolume(volume);
    }
}
