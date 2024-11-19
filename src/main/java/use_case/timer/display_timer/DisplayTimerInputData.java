package use_case.timer.display_timer;

/**
 * Input boundary for the display timer use case.
 */
public class DisplayTimerInputData {
    private final String username;

    public DisplayTimerInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
