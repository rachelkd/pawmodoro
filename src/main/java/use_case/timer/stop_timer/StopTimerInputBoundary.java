package use_case.timer.stop_timer;

/**
 * Input boundary for the StopTimer use case.
 * Defines the contract for stopping the timer.
 */
public interface StopTimerInputBoundary {
    /**
     * Stops the timer using the provided input data.
     *
     * @param inputData The input data for stopping the timer.
     */
    void stopTimer(StopTimerInputData inputData);
}
