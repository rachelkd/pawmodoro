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
}
