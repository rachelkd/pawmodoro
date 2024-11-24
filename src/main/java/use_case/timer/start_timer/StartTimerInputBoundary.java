package use_case.timer.start_timer;

/**
 * Input boundary for the StartTimer use case.
 */
public interface StartTimerInputBoundary {
    /**
     * Starts the timer using the provided input data.
     *
     * @param inputData The input data for starting the timer.
     */
    void startTimer(StartTimerInputData inputData);
}
