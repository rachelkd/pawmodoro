package use_case.timer.stop_timer;

import constants.Constants;
import entity.Timer;
import entity.TimerFactory;
import use_case.timer.TimerOutputBoundary;
import use_case.timer.TimerOutputData;

/**
 * Interactor for stopping the timer.
 * Implements the StopTimerInputBoundary and handles the business logic for stopping the timer.
 */
public class StopTimerInteractor implements StopTimerInputBoundary {
    private final TimerFactory timerFactory;
    private final TimerOutputBoundary outputBoundary;

    /**
     * Constructs a StopTimerInteractor.
     *
     * @param timerFactory   The factory used to create timer entities.
     * @param outputBoundary The output boundary used to communicate with the presenter.
     */
    public StopTimerInteractor(TimerFactory timerFactory, TimerOutputBoundary outputBoundary) {
        this.timerFactory = timerFactory;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Stops the timer using the provided input data.
     *
     * @param inputData The input data for stopping the timer.
     */
    @Override
    public void stopTimer(StopTimerInputData inputData) {

        // Create a new Timer entity with a "STOPPED" status
        // Assuming that the current timer settings are preserved in the Timer entity
        final Timer timer = timerFactory.create("STOPPED", "WORK", 0,
                Constants.DEFAULT_WORK_DURATION_MS);

        // Convert the Timer entity to TimerOutputData to pass to the output boundary (Presenter)
        final TimerOutputData timerOutputData = new TimerOutputData(
                timer.getStatus(),
                timer.getCurrentInterval(),
                timer.getElapsedTime(),
                timer.getIntervalDuration()
        );

        // Notify the presenter to update the view model
        outputBoundary.updateTimerState(timerOutputData);
    }
}

