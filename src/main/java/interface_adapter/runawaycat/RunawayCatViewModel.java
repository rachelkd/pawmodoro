package interface_adapter.runawaycat;

import interface_adapter.ViewModel;

public class RunawayCatViewModel extends ViewModel<RunawayCatState> {
    
    public RunawayCatViewModel() {
        super("runaway cat");
        setState(new RunawayCatState());
    }
} 