package use_case.timer.start_timer;

/**
 * Input Boundary for Start Timer Use Case.
 */
public interface StartTimerInputBoundary {
    /**
     * Executes Start Timer Use Case.
     * @param startTimerInputData the input data
     */
    void execute(StartTimerInputData startTimerInputData);
}
