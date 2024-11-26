package interface_adapter.timer;

import interface_adapter.ViewModel;

/**
 * ViewModel for timer-related view updates. Manages the state of the timer
 * and notifies observers of any changes.
 */
public class TimerViewModel extends ViewModel<TimerState> {

    public TimerViewModel() {
        super("timer");
        setState(new TimerState());
    }

    /**
     * Updates the TimerState and notifies observers.
     *
     * @param status          The updated status of the timer (e.g., RUNNING, STOPPED).
     * @param currentInterval The updated interval type (e.g., WORK, SHORT_BREAK).
     * @param elapsedTime     The updated elapsed time in milliseconds.
     * @param intervalDuration The updated interval duration in milliseconds.
     */
    public void updateState(String status, String currentInterval, long elapsedTime, long intervalDuration) {
        final TimerState state = getState();
        state.updateState(status, currentInterval, elapsedTime, intervalDuration);
        firePropertyChanged();
    }

    /**
     * Resets the TimerState to default values and notifies observers.
     */
    public void resetState() {
        final TimerState state = getState();
        state.resetState();
        firePropertyChanged();
    }
}

