package use_case.music_control;

public interface MusicControlOutputBoundary {
    void prepareSuccessView(boolean isPlaying);
    void prepareFailView(String error);
}
