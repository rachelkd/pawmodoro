package entity;

/**
 * Represents a timer entity in our program.
 */
public class Timer {
    private final String status;
    private final String currentInterval;
    private final long elapsedTime;
    private final long intervalDuration;

    /**
     * Creates a new Timer with the specified properties.
     * 
     * @param status the current status of the timer (RUNNING, PAUSED, STOPPED)
     * @param currentInterval the current interval type (WORK, BREAK)
     * @param elapsedTime the elapsed time in milliseconds
     * @param intervalDuration the total duration of the interval in milliseconds
     */
    public Timer(String status, String currentInterval,
            long elapsedTime, long intervalDuration) {
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
