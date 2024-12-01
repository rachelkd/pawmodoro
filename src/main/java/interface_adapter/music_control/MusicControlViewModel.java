package interface_adapter.music_control;

import interface_adapter.ViewModel;

/**
 * The view model for music_control usecase.
 */
public class MusicControlViewModel extends ViewModel<MusicControlState> {
    public MusicControlViewModel() {
        super("music");
        setState(new MusicControlState());
    }
}
