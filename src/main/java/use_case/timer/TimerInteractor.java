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
        try {
            switch (timerInputData.getAction()) {
                case "start" -> executeStart();
                case "pause" -> executePause();
                case "resume" -> executeResume();
                case "stop" -> executeStop();
                default -> timerPresenter.prepareFailView(
                        "Invalid timer action: " + timerInputData.getAction());
            }
        } catch (Exception exception) {
            timerPresenter.prepareFailView(exception.getMessage());
        }
    }

    private void executeStart() {
        this.timer = timerFactory.createRunningTimer(
                timer.getCurrentInterval(),
                timer.getElapsedTime(),
                timer.getIntervalDuration()
        );
        updatePresenter();
    }

    private void executePause() {
        this.timer = timerFactory.createPausedTimer(
                timer.getCurrentInterval(),
                timer.getElapsedTime(),
                timer.getIntervalDuration()
        );
        updatePresenter();
    }

    private void executeResume() {
        this.timer = timerFactory.createRunningTimer(
                timer.getCurrentInterval(),
                timer.getElapsedTime(),
                timer.getIntervalDuration()
        );
        updatePresenter();
    }

    private void executeStop() {
        this.timer = timerFactory.createStoppedTimer(
                timer.getCurrentInterval(),
                timer.getIntervalDuration()
        );
        updatePresenter();
    }

    private void updatePresenter() {
        final TimerOutputData outputData = new TimerOutputData(
                timer.getState(),
                timer.getCurrentInterval(),
                timer.getElapsedTime(),
                false
        );
        timerPresenter.prepareSuccessView(outputData);
    }
} 