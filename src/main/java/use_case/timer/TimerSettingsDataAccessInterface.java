package use_case.timer;

/**
 * Interface for accessing timer settings and configuration data.
 */
public interface TimerSettingsDataAccessInterface {
    /**
     * Gets the default work interval duration in milliseconds.
     * 
     * @return work interval duration
     */
    long getWorkDuration();

    /**
     * Gets the default short break duration in milliseconds.
     * 
     * @return short break duration
     */
    long getShortBreakDuration();

    /**
     * Gets the default long break duration in milliseconds.
     * 
     * @return long break duration
     */
    long getLongBreakDuration();

    /**
     * Gets the formatted time display for a duration.
     * 
     * @param duration the duration in milliseconds
     * @return formatted time string (e.g., "25:00")
     */
    String formatTimeDisplay(long duration);
}
