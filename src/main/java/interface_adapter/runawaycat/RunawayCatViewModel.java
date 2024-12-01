package interface_adapter.runawaycat;

import interface_adapter.ViewModel;

public class RunawayCatViewModel extends ViewModel<RunawayCatState> {

    public static final String REMINDER_LABEL = "Remember to monitor your cats' health and happiness!";
    public static final String CONFIRM_LABEL = "Okay..";
    public static final String TITLE_LABEL = "Runaway Cat";

    public RunawayCatViewModel() {
        super("runaway cat");
        setState(new RunawayCatState());
    }
} 