package interface_adapter.timer;

import use_case.timer.display_timer.DisplayTimerOutputBoundary;
import use_case.timer.display_timer.DisplayTimerOutputData;

/**
 * Presenter for timer-related view updates
 */
public class TimerPresenter implements DisplayTimerOutputBoundary {
    private final TimerViewModel timerViewModel;

    public TimerPresenter(TimerViewModel timerViewModel) {
        this.timerViewModel = timerViewModel;
    }

    @Override
    public void prepareView(DisplayTimerOutputData outputData) {
        final TimerState timerState = new TimerState();
        timerState.setStatus(outputData.getStatus());
        timerState.setCurrentInterval(outputData.getCurrentInterval());
        timerState.setElapsedTime(outputData.getElapsedTime());
        timerState.setIntervalDuration(outputData.getIntervalDuration());

        timerViewModel.setState(timerState);
        timerViewModel.firePropertyChanged();
    }
}
