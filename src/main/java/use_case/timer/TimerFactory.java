package use_case.timer;

import entity.Timer;
import interface_adapter.timer.TimerState;

/**
 * Factory for creating Timer entities.
 */
public class TimerFactory {

    /**
     * Creates a new Timer with running state.
     * 
     * @param currentInterval the current interval type
     * @param elapsedTime the elapsed time in milliseconds
     * @param intervalDuration the total duration of the interval
     * @return a new Timer instance
     */
    public Timer createRunningTimer(String currentInterval,
            long elapsedTime,
            long intervalDuration) {
        return new Timer(TimerState.running(),
                currentInterval,
                elapsedTime,
                intervalDuration);
    }

    /**
     * Creates a new Timer with paused state.
     * 
     * @param currentInterval the current interval type
     * @param elapsedTime the elapsed time in milliseconds
     * @param intervalDuration the total duration of the interval
     * @return a new Timer instance
     */
    public Timer createPausedTimer(String currentInterval,
            long elapsedTime,
            long intervalDuration) {
        return new Timer(TimerState.paused(),
                currentInterval,
                elapsedTime,
                intervalDuration);
    }

    /**
     * Creates a new Timer with stopped state.
     * 
     * @param currentInterval the current interval type
     * @param intervalDuration the total duration of the interval
     * @return a new Timer instance
     */
    public Timer createStoppedTimer(String currentInterval,
            long intervalDuration) {
        return new Timer(TimerState.stopped(),
                currentInterval,
                0,
                intervalDuration);
    }
}
