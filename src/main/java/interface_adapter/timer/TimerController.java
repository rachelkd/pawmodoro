package interface_adapter.timer;

import use_case.timer.display_timer.DisplayTimerInputBoundary;
import use_case.timer.display_timer.DisplayTimerInputData;

/**
 * Controller for timer-related user actions
 */
public class TimerController {
    private final DisplayTimerInputBoundary displayTimerUseCaseInteractor;

    public TimerController(DisplayTimerInputBoundary displayTimerUseCaseInteractor) {
        this.displayTimerUseCaseInteractor = displayTimerUseCaseInteractor;
    }

    /**
     * Executes the display timer use case for a specific user
     * 
     * @param username the username of the user whose timer to display
     */
    public void execute(String username) {
        final DisplayTimerInputData displayTimerInputData = new DisplayTimerInputData(username);
        displayTimerUseCaseInteractor.execute(displayTimerInputData);
    }
}
