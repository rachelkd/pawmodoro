package use_case.music_control;

/**
 * The Output Boundary for music_control usecase.
 */
public interface MusicControlOutputBoundary {

    /**
     * Prepares the Success View of music_control.
     *
     * @param isPlaying the playback state of the music.
     */
    void prepareSuccessView(boolean isPlaying);

    /**
     * Prepares the Fail View of music_control.
     *
     * @param error error message of the music controls.
     */
    void prepareFailView(String error);
}
