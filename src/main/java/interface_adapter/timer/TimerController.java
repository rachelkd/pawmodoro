package interface_adapter.timer;

import use_case.timer.start_timer.StartTimerInputBoundary;
import use_case.timer.start_timer.StartTimerInputData;
import use_case.timer.stop_timer.StopTimerInputBoundary;
import use_case.timer.stop_timer.StopTimerInputData;

/**
 * Controller for timer-related user actions.
 * Serves as the bridge between the view and the use cases.
 */
public class TimerController {
    private final StartTimerInputBoundary startTimerUseCase;
    private final StopTimerInputBoundary stopTimerUseCase;

    /**
     * Constructs a TimerController.
     *
     * @param startTimerUseCase The input boundary for starting the timer.
     * @param stopTimerUseCase  The input boundary for stopping the timer.
     */
    public TimerController(StartTimerInputBoundary startTimerUseCase, StopTimerInputBoundary stopTimerUseCase) {
        this.startTimerUseCase = startTimerUseCase;
        this.stopTimerUseCase = stopTimerUseCase;
    }

    /**
     * Handles the request to start the timer.
     *
     * @param username The username of the user starting the timer.
     */
    public void startTimer(String username) {
        startTimerUseCase.execute(new StartTimerInputData(username));
    }

    /**
     * Handles the request to stop the timer.
     *
     * @param username The username of the user stopping the timer.
     */
    public void stopTimer(String username) {
        stopTimerUseCase.execute(new StopTimerInputData(username));
    }
}
