package use_case.timer;

import entity.Timer;

/**
 * Interface for persisting timer state.
 */
public interface TimerStatusDataAccessInterface {
    /**
     * Saves the current state of a timer.
     * 
     * @param username the username of the user
     * @param timer the timer to save
     */
    void save(String username, Timer timer);
}
