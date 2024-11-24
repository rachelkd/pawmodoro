package interface_adapter.music_control;

import interface_adapter.ViewModel;

public class MusicControlViewModel extends ViewModel<MusicControlState> {
    public MusicControlViewModel() {
        super("music");
        setState(new MusicControlState());
    }
}
