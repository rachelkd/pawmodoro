package entity;

/**
 * Represents a timer entity in our program.
 */
public class Timer {
    private final String state;
    private final String currentInterval;
    private final long elapsedTime;
    private final long intervalDuration;

    /**
     * Creates a new Timer with the specified properties.
     * 
     * @param state the current state of the timer
     * @param currentInterval the current interval type
     * @param elapsedTime the elapsed time in milliseconds
     * @param intervalDuration the total duration of the interval in milliseconds
     */
    public Timer(String state, String currentInterval,
            long elapsedTime, long intervalDuration) {
        this.state = state;
        this.currentInterval = currentInterval;
        this.elapsedTime = elapsedTime;
        this.intervalDuration = intervalDuration;
    }

    public String getState() {
        return state;
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
