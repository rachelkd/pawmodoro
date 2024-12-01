package interface_adapter.music_control;

import use_case.music_control.MusicControlInputBoundary;

/**
 * The controller for music playback controls.
 */
public class MusicControlController {
    private final MusicControlInputBoundary musicInteractor;
    
    public MusicControlController(MusicControlInputBoundary musicInteractor) {
        this.musicInteractor = musicInteractor;
    }

    /**
     * Toggles playback.
     */
    public void togglePlayback() {
        musicInteractor.togglePlayback();
    }

}
