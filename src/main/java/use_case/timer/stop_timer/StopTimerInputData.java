package use_case.timer.stop_timer;

/**
 * Input data for the StopTimer use case.
 */
public class StopTimerInputData {
    private final String username;

    /**
     * Constructs a StopTimerInputData object.
     *
     * @param username The username associated with the timer.
     */
    public StopTimerInputData(String username) {
        this.username = username;
    }

    /**
     * Gets the username associated with the timer.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }
}
