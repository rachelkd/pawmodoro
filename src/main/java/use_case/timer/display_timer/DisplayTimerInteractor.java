package use_case.timer.display_timer;

import entity.Timer;

/**
 * Use case interactor for displaying timer information.
 */
public class DisplayTimerInteractor implements DisplayTimerInputBoundary {
    private final DisplayTimerDataAccessInterface timerDataAccess;
    private final DisplayTimerOutputBoundary timerPresenter;

    public DisplayTimerInteractor(DisplayTimerDataAccessInterface timerDataAccess,
            DisplayTimerOutputBoundary timerPresenter) {
        this.timerDataAccess = timerDataAccess;
        this.timerPresenter = timerPresenter;
    }

    @Override
    public void execute(DisplayTimerInputData input) {
        final Timer timer = timerDataAccess.getTimer(input.getUsername());

        final DisplayTimerOutputData outputData = new DisplayTimerOutputData(
                timer.getStatus(),
                timer.getCurrentInterval(),
                timer.getElapsedTime(),
                timer.getIntervalDuration());

        timerPresenter.prepareView(outputData);
    }
}
