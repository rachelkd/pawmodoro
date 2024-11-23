package use_case.timer.start_timer;

import entity.Timer;
import entity.TimerFactory;

/**
 * Interactor for the Start Timer Use Case.
 * Implements the use case logic for starting the timer.
 */
public class StartTimerInteractor implements StartTimerInputBoundary {
    private final TimerFactory timerFactory;
    private final StartTimerOutputBoundary startTimerOutputBoundary;
    private Timer currentTimer;
    private long startTime;

    /**
     * Constructs a StartTimerInteractor object.
     *
     * @param timerFactory   The factory for creating Timer entities.
     * @param startTimerOutputBoundary The output boundary for presenting the output data.
     */
    public StartTimerInteractor(TimerFactory timerFactory, StartTimerOutputBoundary startTimerOutputBoundary) {
        this.timerFactory = timerFactory;
        this.startTimerOutputBoundary = startTimerOutputBoundary;
        this.currentTimer = timerFactory.create();
    }

    /**
     * Executes the start timer use case.
     *
     * @param startTimerInputData The input data for starting the timer.
     */
    @Override
    public void execute(StartTimerInputData startTimerInputData) {
        if (!"RUNNING".equals(currentTimer.getStatus())) {
            this.startTime = System.currentTimeMillis();
            this.currentTimer = timerFactory.create(
                    "RUNNING",
                    currentTimer.getCurrentInterval(),
                    currentTimer.getElapsedTime(),
                    currentTimer.getIntervalDuration()
            );
        }

        final StartTimerOutputData startTimerOutputData = new StartTimerOutputData(
                currentTimer.getStatus(),
                currentTimer.getCurrentInterval(),
                currentTimer.getElapsedTime(),
                currentTimer.getIntervalDuration()
        );

        startTimerOutputBoundary.present(startTimerOutputData);
    }
}
