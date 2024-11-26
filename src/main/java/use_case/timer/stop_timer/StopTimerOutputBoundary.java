package use_case.timer.stop_timer;

/**
 * Output boundary for the Stop Timer Use Case.
 * Defines the method to present the output data.
 */
public interface StopTimerOutputBoundary {
    /**
     * Presents the output data of the stop timer operation.
     *
     * @param stopTimerOutputData The output data to be presented.
     */
    void present(StopTimerOutputData stopTimerOutputData);
}
