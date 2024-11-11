package use_case.timer.display_timer;

import entity.Timer;

/**
 * Interface for Timer data persistence operations
 */
public interface DisplayTimerDataAccessInterface {
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
}
