package interface_adapter.break_session;

import interface_adapter.ViewModel;

/**
 * View Model of Break Session.
 */
public class BreakSessionViewModel extends ViewModel<BreakSessionState> {

    public BreakSessionViewModel() {
        super("break session");
        setState(new BreakSessionState());
    }
}
