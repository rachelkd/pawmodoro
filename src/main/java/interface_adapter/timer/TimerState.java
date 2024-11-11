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

    // Copy constructor
    public TimerState(TimerState copy) {
        this.status = copy.status;
        this.currentInterval = copy.currentInterval;
        this.elapsedTime = copy.elapsedTime;
        this.intervalDuration = copy.intervalDuration;
    }

    public TimerState() {
        // Default constructor
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrentInterval() {
        return currentInterval;
    }

    public void setCurrentInterval(String currentInterval) {
        this.currentInterval = currentInterval;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public long getIntervalDuration() {
        return intervalDuration;
    }

    public void setIntervalDuration(long intervalDuration) {
        this.intervalDuration = intervalDuration;
    }
}
