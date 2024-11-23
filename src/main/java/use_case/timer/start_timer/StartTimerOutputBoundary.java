package use_case.timer.start_timer;

/**
 * Output boundary for the Start Timer Use Case.
 * Defines the method to present the output data.
 */
public interface StartTimerOutputBoundary {
    /**
     * Presents the output data of the start timer operation.
     *
     * @param startTimerOutputData The output data to be presented.
     */
    void present(StartTimerOutputData startTimerOutputData);
}
