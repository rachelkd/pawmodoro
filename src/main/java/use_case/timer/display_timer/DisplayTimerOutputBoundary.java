package use_case.timer.display_timer;

/**
 * Output boundary for the display timer use case.
 */
public interface DisplayTimerOutputBoundary {
    /**
     * Prepare the view with timer data.
     * 
     * @param timer the timer data to display
     */
    void prepareView(DisplayTimerOutputData timer);
}
