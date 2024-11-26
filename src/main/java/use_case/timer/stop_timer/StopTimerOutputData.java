package use_case.timer.stop_timer;

/**
 * Represents the output data after the stop timer operation.
 */
public class StopTimerOutputData {
    private final String status;
    private final String currentInterval;
    private final long elapsedTime;
    private final long intervalDuration;

    /**
     * Constructs a StopTimerOutputData object.
     *
     * @param status          The status of the timer (e.g., STOPPED).
     * @param currentInterval The current interval type (e.g., WORK).
     * @param elapsedTime     The elapsed time in milliseconds.
     * @param intervalDuration The total duration of the interval in milliseconds.
     */
    public StopTimerOutputData(String status, String currentInterval, long elapsedTime, long intervalDuration) {
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
