package entity;

import constants.Constants;

/**
 * Factory for creating Timer objects with standardized initialization.
 */
public class TimerFactory {
    /**
     * Creates a new Timer with default settings (25 minutes work interval, stopped).
     * 
     * @return a new Timer instance with default settings
     */
    public Timer create() {
        return new Timer("STOPPED", "WORK", 0, Constants.DEFAULT_WORK_DURATION_MS);
    }

    /**
     * Creates a new Timer with specified settings.
     * 
     * @param status current timer status
     * @param currentInterval type of interval
     * @param elapsedTime time elapsed in milliseconds
     * @param intervalDuration total duration in milliseconds
     * @return a new Timer instance with the specified settings
     */
    public Timer create(String status, String currentInterval,
            long elapsedTime, long intervalDuration) {
        return new Timer(status, currentInterval, elapsedTime, intervalDuration);
    }
}
