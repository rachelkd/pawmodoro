package use_case.timer.stop_timer;

import entity.Timer;
import entity.TimerFactory;

/**
 * Interactor for the Stop Timer Use Case.
 * Implements the use case logic for stopping the timer.
 */
public class StopTimerInteractor implements StopTimerInputBoundary {
    private final TimerFactory timerFactory;
    private final StopTimerOutputBoundary stopTimerOutputBoundary;
    private Timer currentTimer;
    private long startTime;

    /**
     * Constructs a StopTimerInteractor object.
     *
     * @param timerFactory   The factory for creating Timer entities.
     * @param stopTimerOutputBoundary1 The output boundary for presenting the output data.
     */
    public StopTimerInteractor(TimerFactory timerFactory, StopTimerOutputBoundary stopTimerOutputBoundary1) {
        this.timerFactory = timerFactory;
        this.stopTimerOutputBoundary = stopTimerOutputBoundary1;
        this.currentTimer = timerFactory.create();
    }

    /**
     * Executes the stop timer use case.
     *
     * @param stopTimerInputData The input data for stopping the timer.
     */
    @Override
    public void execute(StopTimerInputData stopTimerInputData) {
        if ("RUNNING".equals(currentTimer.getStatus())) {
            final long elapsedTime = System.currentTimeMillis() - startTime + currentTimer.getElapsedTime();
            this.currentTimer = timerFactory.create(
                    "STOPPED",
                    currentTimer.getCurrentInterval(),
                    elapsedTime,
                    currentTimer.getIntervalDuration()
            );
        }

        final StopTimerOutputData stopTimerOutputData = new StopTimerOutputData(
                currentTimer.getStatus(),
                currentTimer.getCurrentInterval(),
                currentTimer.getElapsedTime(),
                currentTimer.getIntervalDuration()
        );

        stopTimerOutputBoundary.present(stopTimerOutputData);
    }
}
