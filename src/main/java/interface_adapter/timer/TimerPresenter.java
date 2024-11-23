package interface_adapter.timer;

import use_case.timer.start_timer.StartTimerOutputBoundary;
import use_case.timer.start_timer.StartTimerOutputData;
import use_case.timer.stop_timer.StopTimerOutputBoundary;
import use_case.timer.stop_timer.StopTimerOutputData;

/**
 * Presenter for timer-related view updates.
 * Transforms use case output data into a format suitable for the view model.
 */
public class TimerPresenter implements StartTimerOutputBoundary, StopTimerOutputBoundary {
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
     * Updates the view model with the output data from the start timer use case.
     *
     * @param startTimerOutputData The output data from the start timer operation.
     */
    @Override
    public void present(StartTimerOutputData startTimerOutputData) {
        updateViewModel(startTimerOutputData.getStatus(), startTimerOutputData.getCurrentInterval(),
                startTimerOutputData.getElapsedTime(), startTimerOutputData.getIntervalDuration());
    }

    /**
     * Updates the view model with the output data from the stop timer use case.
     *
     * @param stopTimerOutputData The output data from the stop timer operation.
     */
    @Override
    public void present(StopTimerOutputData stopTimerOutputData) {
        updateViewModel(stopTimerOutputData.getStatus(), stopTimerOutputData.getCurrentInterval(),
                stopTimerOutputData.getElapsedTime(), stopTimerOutputData.getIntervalDuration());
    }

    private void updateViewModel(String status, String currentInterval, long elapsedTime, long intervalDuration) {
        final TimerState state = new TimerState();
        state.setStatus(status);
        state.setCurrentInterval(currentInterval);
        state.setElapsedTime(elapsedTime);
        state.setIntervalDuration(intervalDuration);

        timerViewModel.setState(state);
        timerViewModel.firePropertyChanged();
    }
}