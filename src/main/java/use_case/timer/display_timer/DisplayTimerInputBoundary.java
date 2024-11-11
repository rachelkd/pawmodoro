package use_case.timer.display_timer;

public interface DisplayTimerInputBoundary {
    /**
     * Execute the display timer use case
     * 
     * @param input the input data containing the username
     */
    void execute(DisplayTimerInputData input);
}
