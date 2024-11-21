package use_case.timer;

import entity.Timer;

/**
 * Unified interface for timer operations, including persistence and settings.
 */
public interface TimerDataAccessInterface {
    /**
     * Gets the timer for a specific user.
     * 
     * @param username the username of the user
     * @return the Timer entity for the user
     */
    Timer getTimer(String username);

    /**
     * Saves the timer state for a specific user.
     * 
     * @param username the username of the user
     * @param timer the timer to save
     */
    void save(String username, Timer timer);

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
