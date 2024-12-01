package interface_adapter.music_control;

import use_case.music_control.MusicControlOutputBoundary;

/**
 * The presenter for the music_control usecase.
 */
public class MusicControlPresenter implements MusicControlOutputBoundary {
    private final MusicControlViewModel musicControlViewModel;

    public MusicControlPresenter(MusicControlViewModel musicControlViewModel) {
        this.musicControlViewModel = musicControlViewModel;
    }

    @Override
    public void prepareSuccessView(boolean isPlaying) {
        final MusicControlState state = musicControlViewModel.getState();
        final String buttonText;
        state.setPlaying(isPlaying);
        if (isPlaying) {
            buttonText = "Pause Ambient Noise";
        }
        else {
            buttonText = "Play Ambient Noise";
        }
        state.setButtonText(buttonText);
        musicControlViewModel.setState(state);
        musicControlViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final MusicControlState state = musicControlViewModel.getState();
        state.setError(error);
        musicControlViewModel.setState(state);
        musicControlViewModel.firePropertyChanged();
    }
}
