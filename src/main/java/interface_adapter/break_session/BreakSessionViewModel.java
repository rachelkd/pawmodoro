package interface_adapter.break_session;

import interface_adapter.ViewModel;

/**
 * View Model of Break Session.
 */
public class BreakSessionViewModel extends ViewModel<BreakSessionState> {
    public static String ADOPTION_LABEL = "Adopt a new cat!";
    public BreakSessionViewModel() {
        super("break session");
        setState(new BreakSessionState());
    }
}
