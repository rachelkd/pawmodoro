package use_case.timer.start_timer;

/**
 * Represents the input data required to start the timer.
 */
public class StartTimerInputData {
    private final String username;
    private final long intervalDuration;

    /**
     * Constructs a StartTimerInputData object.
     *
     * @param username The username of the user who is starting the timer.
     * @param intervalDuration The duration of the timer interval in milliseconds.
     */
    public StartTimerInputData(String username, long intervalDuration) {
        this.username = username;
        this.intervalDuration = intervalDuration;
    }

    /**
     * Gets the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the duration of the timer interval in milliseconds.
     *
     * @return The interval duration.
     */
    public long getIntervalDuration() {
        return intervalDuration;
    }
}
