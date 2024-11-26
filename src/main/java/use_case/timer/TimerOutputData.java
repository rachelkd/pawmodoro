package use_case.timer;

/**
 * Output data for timer-related use cases.
 */
public class TimerOutputData {
    private final String status;
    private final String currentInterval;
    private final long elapsedTime;
    private final long intervalDuration;

    /**
     * Constructs a TimerOutputData object.
     *
     * @param status          The status of the timer (e.g., "RUNNING").
     * @param currentInterval The current interval type (e.g., "WORK").
     * @param elapsedTime     The elapsed time in milliseconds.
     * @param intervalDuration The interval duration in milliseconds.
     */
    public TimerOutputData(String status, String currentInterval, long elapsedTime, long intervalDuration) {
        this.status = status;
        this.currentInterval = currentInterval;
        this.elapsedTime = elapsedTime;
        this.intervalDuration = intervalDuration;
    }

    public String getStatus() {
        return status;
    }

    public String getCurrentInterval() {
        return currentInterval;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public long getIntervalDuration() {
        return intervalDuration;
    }
}

