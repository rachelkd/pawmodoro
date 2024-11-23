package interface_adapter.timer;

import constants.Constants;

/**
 * Represents the current state of a timer, including its status, interval type,
 * elapsed time, and total duration. This state object is used to communicate
 * timer information between different layers of the application.
 */
public class TimerState {
    private String status = Constants.STATUS_STOPPED;
    private String currentInterval = Constants.INTERVAL_WORK;
    private long elapsedTime;
    private long intervalDuration = Constants.DEFAULT_WORK_DURATION_MS;

    /**
     * Constructs a TimerState by copying another TimerState.
     *
     * @param copy The TimerState to copy.
     */
    public TimerState(TimerState copy) {
        this.status = copy.status;
        this.currentInterval = copy.currentInterval;
        this.elapsedTime = copy.elapsedTime;
        this.intervalDuration = copy.intervalDuration;
    }

    /**
     * Default constructor for TimerState.
     */
    public TimerState() {
        // Default constructor
    }

    /**
     * Gets the current status of the timer.
     *
     * @return The status of the timer (e.g., RUNNING, STOPPED).
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the current status of the timer.
     *
     * @param status The new status of the timer.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the current interval type of the timer.
     *
     * @return The interval type (e.g., WORK, SHORT_BREAK).
     */
    public String getCurrentInterval() {
        return currentInterval;
    }

    /**
     * Sets the current interval type of the timer.
     *
     * @param currentInterval The new interval type.
     */
    public void setCurrentInterval(String currentInterval) {
        this.currentInterval = currentInterval;
    }

    /**
     * Gets the elapsed time of the timer in milliseconds.
     *
     * @return The elapsed time in milliseconds.
     */
    public long getElapsedTime() {
        return elapsedTime;
    }

    /**
     * Sets the elapsed time of the timer in milliseconds.
     *
     * @param elapsedTime The new elapsed time in milliseconds.
     */
    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    /**
     * Gets the total duration of the current interval in milliseconds.
     *
     * @return The interval duration in milliseconds.
     */
    public long getIntervalDuration() {
        return intervalDuration;
    }

    /**
     * Sets the total duration of the current interval in milliseconds.
     *
     * @param intervalDuration The new interval duration in milliseconds.
     */
    public void setIntervalDuration(long intervalDuration) {
        this.intervalDuration = intervalDuration;
    }

    /**
     * Updates the timer state with new values.
     *
     * @param status          The updated status of the timer.
     * @param currentInterval The updated interval type.
     * @param elapsedTime     The updated elapsed time in milliseconds.
     * @param intervalDuration The updated total duration of the interval in milliseconds.
     */
    public void updateState(String status, String currentInterval, long elapsedTime, long intervalDuration) {
        this.status = status;
        this.currentInterval = currentInterval;
        this.elapsedTime = elapsedTime;
        this.intervalDuration = intervalDuration;
    }

    /**
     * Resets the timer state to default values.
     */
    public void resetState() {
        this.status = Constants.STATUS_STOPPED;
        this.currentInterval = Constants.INTERVAL_WORK;
        this.elapsedTime = 0;
        this.intervalDuration = Constants.DEFAULT_WORK_DURATION_MS;
    }
}
