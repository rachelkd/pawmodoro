package interface_adapter.music_control;

/**
 * The state for music_control usecase.
 */
public class MusicControlState {
    private boolean isPlaying;
    private String buttonText = "Play";
    private String error = "";

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
