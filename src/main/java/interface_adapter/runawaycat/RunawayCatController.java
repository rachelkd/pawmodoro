package interface_adapter.runawaycat;

public class RunawayCatController {
    private final RunawayCatViewModel runawayCatViewModel;
    
    public RunawayCatController(RunawayCatViewModel runawayCatViewModel) {
        this.runawayCatViewModel = runawayCatViewModel;
    }

    public void handleConfirm() {
        // TODO: Implement confirmation logic
        // This should probably update some state in the app to acknowledge
        // that the user has seen the runaway cat message
    }
} 