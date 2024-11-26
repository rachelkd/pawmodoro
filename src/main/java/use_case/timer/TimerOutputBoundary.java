package use_case.timer;

/**
 * Output boundary for timer-related use cases.
 */
public interface TimerOutputBoundary {
    /**
     * Updates the timer state in the presenter.
     *
     * @param outputData The output data from the use case.
     */
    void updateTimerState(TimerOutputData outputData);
}
