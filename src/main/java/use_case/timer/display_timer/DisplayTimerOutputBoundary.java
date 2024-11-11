package use_case.timer.display_timer;

public interface DisplayTimerOutputBoundary {
    /**
     * Prepare the view with timer data
     * 
     * @param timer the timer data to display
     */
    void prepareView(DisplayTimerOutputData timer);
}
