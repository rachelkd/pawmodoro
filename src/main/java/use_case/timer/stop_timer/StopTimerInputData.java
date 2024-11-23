package use_case.timer.stop_timer;

/**
 * Represents the input data required to stop the timer.
 */
public class StopTimerInputData {
    private final String username;

    /**
     * Constructs a StopTimerInputData object.
     *
     * @param username The username of the user who is stopping the timer.
     */
    public StopTimerInputData(String username) {
        this.username = username;
    }

    /**
     * Gets the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }
}
