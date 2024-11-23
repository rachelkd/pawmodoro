package use_case.timer.stop_timer;

/**
 * Input boundary for the Stop Timer Use Case.
 * Defines the method to execute the stop timer operation.
 */
public interface StopTimerInputBoundary {
    /**
     * Executes the stop timer use case.
     *
     * @param stopTimerInputData The input data for stopping the timer.
     */
    void execute(StopTimerInputData stopTimerInputData);
}
