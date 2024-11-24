package interface_adapter.music_control;

import use_case.music_control.MusicControlOutputBoundary;

public class MusicControlPresenter implements MusicControlOutputBoundary {
    private final MusicControlViewModel musicControlViewModel;

    public MusicControlPresenter(MusicControlViewModel musicControlViewModel) {
        this.musicControlViewModel = musicControlViewModel;
    }

    @Override
    public void prepareSuccessView(boolean isPlaying) {
        MusicControlState state = musicControlViewModel.getState();
        state.setPlaying(isPlaying);
        state.setButtonText(isPlaying ? "Pause" : "Play");
        musicControlViewModel.setState(state);
        musicControlViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        MusicControlState state = musicControlViewModel.getState();
        state.setError(error);
        musicControlViewModel.setState(state);
        musicControlViewModel.firePropertyChanged();
    }
}
