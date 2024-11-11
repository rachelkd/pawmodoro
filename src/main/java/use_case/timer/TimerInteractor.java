package use_case.timer;

import entity.Timer;

/**
 * Implements timer use case logic.
 */
public class TimerInteractor implements TimerInputBoundary {
    private Timer timer;
    private final TimerOutputBoundary timerPresenter;
    private final TimerFactory timerFactory;

    /**
     * Creates a new TimerInteractor.
     * 
     * @param timer the timer entity
     * @param timerPresenter the output boundary
     * @param timerFactory the timer factory
     */
    public TimerInteractor(Timer timer,
            TimerOutputBoundary timerPresenter,
            TimerFactory timerFactory) {
        this.timer = timer;
        this.timerPresenter = timerPresenter;
        this.timerFactory = timerFactory;
    }

    @Override
    public void execute(TimerInputData timerInputData) {
        final String action = timerInputData.getAction();
        if ("start".equals(action)) {
            executeStart();
        }
        else if ("pause".equals(action)) {
            executePause();
        }
        else if ("resume".equals(action)) {
            executeResume();
        }
        else if ("stop".equals(action)) {
            executeStop();
        }
        else {
            timerPresenter.prepareFailView("Invalid timer action: " + action);
        }
    }

    private void executeStart() {
        this.timer = timerFactory.createRunningTimer(
                timer.getCurrentInterval(),
                timer.getElapsedTime(),
                timer.getIntervalDuration());
        updatePresenter();
    }

    private void executePause() {
        this.timer = timerFactory.createPausedTimer(
                timer.getCurrentInterval(),
                timer.getElapsedTime(),
                timer.getIntervalDuration());
        updatePresenter();
    }

    private void executeResume() {
        this.timer = timerFactory.createRunningTimer(
                timer.getCurrentInterval(),
                timer.getElapsedTime(),
                timer.getIntervalDuration());
        updatePresenter();
    }

    private void executeStop() {
        this.timer = timerFactory.createStoppedTimer(
                timer.getCurrentInterval(),
                timer.getIntervalDuration());
        updatePresenter();
    }

    private void updatePresenter() {
        final TimerOutputData outputData = new TimerOutputData(
                timer.getState(),
                timer.getCurrentInterval(),
                timer.getElapsedTime(),
                false);
        timerPresenter.prepareSuccessView(outputData);
    }
}
