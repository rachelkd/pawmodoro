package use_case.timer.start_timer;

import entity.Timer;
import entity.TimerFactory;
import use_case.timer.TimerOutputBoundary;
import use_case.timer.TimerOutputData;

/**
 * Interactor for starting the timer.
 * Implements the StartTimerInputBoundary and handles the business logic for starting the timer.
 */
public class StartTimerInteractor implements StartTimerInputBoundary {
    private final TimerFactory timerFactory;
    private final TimerOutputBoundary outputBoundary;

    /**
     * Constructs a StartTimerInteractor.
     *
     * @param timerFactory   The factory used to create timer entities.
     * @param outputBoundary The output boundary used to communicate with the presenter.
     */
    public StartTimerInteractor(TimerFactory timerFactory, TimerOutputBoundary outputBoundary) {
        this.timerFactory = timerFactory;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Starts the timer using the provided input data.
     *
     * @param inputData The input data for starting the timer.
     */
    @Override
    public void startTimer(StartTimerInputData inputData) {
        final long intervalDuration = inputData.getIntervalDuration();

        // Business logic: Create a new Timer entity with a "RUNNING" status
        final Timer timer = timerFactory.create("RUNNING", "WORK", 0, intervalDuration);

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