package interface_adapter.timer;

import use_case.timer.TimerOutputBoundary;
import use_case.timer.TimerOutputData;

/**
 * Presenter for timer-related view updates.
 * Transforms use case output data into a format suitable for the view model.
 */
public class TimerPresenter implements TimerOutputBoundary {
    private final TimerViewModel timerViewModel;

    /**
     * Constructs a TimerPresenter.
     *
     * @param timerViewModel The view model to update with timer state.
     */
    public TimerPresenter(TimerViewModel timerViewModel) {
        this.timerViewModel = timerViewModel;
    }

    /**
     * Updates the timer state in the view model and notifies observers.
     *
     * @param outputData The data provided by the use case.
     */
    @Override
    public void updateTimerState(TimerOutputData outputData) {
        final TimerState state = timerViewModel.getState();
        state.setStatus(outputData.getStatus());
        state.setCurrentInterval(outputData.getCurrentInterval());
        state.setElapsedTime(outputData.getElapsedTime());
        state.setIntervalDuration(outputData.getIntervalDuration());
        timerViewModel.firePropertyChanged();
    }
}
