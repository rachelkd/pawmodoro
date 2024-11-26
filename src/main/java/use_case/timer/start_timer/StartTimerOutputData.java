package use_case.timer.start_timer;

/**
 * Represents the output data after the start timer operation.
 */
public class StartTimerOutputData {
    private final String status;
    private final String currentInterval;
    private final long elapsedTime;
    private final long intervalDuration;

    /**
     * Constructs a StartTimerOutputData object.
     *
     * @param status          The status of the timer (e.g., RUNNING, STOPPING).
     * @param currentInterval The current interval type (e.g., WORK, BREAK).
     * @param elapsedTime     The elapsed time in milliseconds.
     * @param intervalDuration The total duration of the interval in milliseconds.
     */
    public StartTimerOutputData(String status, String currentInterval, long elapsedTime, long intervalDuration) {
        this.status = status;
        this.currentInterval = currentInterval;
        this.elapsedTime = elapsedTime;
        this.intervalDuration = intervalDuration;
    }

    /**
     * Gets the timer's status.
     *
     * @return The status of the timer.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Gets the current interval type.
     *
     * @return The current interval type.
     */
    public String getCurrentInterval() {
        return currentInterval;
    }

    /**
     * Gets the elapsed time in milliseconds.
     *
     * @return The elapsed time.
     */
    public long getElapsedTime() {
        return elapsedTime;
    }

    /**
     * Gets the total interval duration in milliseconds.
     *
     * @return The interval duration.
     */
    public long getIntervalDuration() {
        return intervalDuration;
    }
}
