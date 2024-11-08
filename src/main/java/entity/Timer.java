package entity;

/**
 * Represents a timer that can track different intervals for the Pomodoro
 * technique.
 */
public interface Timer {
    /**
     * Starts or resumes the timer.
     */
    void start();

    /**
     * Pauses the timer, preserving the elapsed time.
     */
    void pause();

    /**
     * Resumes the timer from its paused state.
     */
    void resume();

    /**
     * Stops the timer and resets elapsed time.
     */
    void stop();

    /**
     * Gets the current state of the timer.
     * 
     * @return the current TimerState
     */
    TimerState getState();

    /**
     * Gets the current interval type.
     * 
     * @return the current TimerInterval
     */
    TimerInterval getCurrentInterval();

    /**
     * Gets the elapsed time in milliseconds.
     * 
     * @return the elapsed time
     */
    long getElapsedTime();
}
