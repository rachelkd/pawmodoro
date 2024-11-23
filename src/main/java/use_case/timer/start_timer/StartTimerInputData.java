package use_case.timer.start_timer;

/**
 * Represents the input data required to start the timer.
 */
public class StartTimerInputData {
    private final String username;

    /**
     * Constructs a StartTimerInputData object.
     *
     * @param username The username of the user who is starting the timer.
     */
    public StartTimerInputData(String username) {
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
