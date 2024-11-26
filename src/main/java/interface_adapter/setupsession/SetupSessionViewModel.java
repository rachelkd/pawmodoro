package interface_adapter.setupsession;

import interface_adapter.ViewModel;

/**
 * The view model for the setting up study session use case.
 */
public class SetupSessionViewModel extends ViewModel<SetupSessionState> {
    public static final String TITLE_LABEL = "Setup Study Session View";
    public static final String RETURN_BUTTON_LABEL = "Return";
    public static final String STUDY_LABEL = "Study time: ";
    public static final String BREAK_LABEL = "Break time: ";
    public static final String INSTRUCTIONS_LABEL = "Choose your study intervals";

    public SetupSessionViewModel() {
        super("set up study session");
        setState(new SetupSessionState());
    }
}
