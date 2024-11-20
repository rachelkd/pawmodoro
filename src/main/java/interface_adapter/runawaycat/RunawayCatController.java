package interface_adapter.runawaycat;

import use_case.runawaycat.RunawayCatInputBoundary;

public class RunawayCatController {
    private final RunawayCatViewModel runawayCatViewModel;
    private final RunawayCatInputBoundary runawayCatInputBoundary;

    public RunawayCatController(RunawayCatViewModel runawayCatViewModel, RunawayCatInputBoundary runawayCatInputBoundary) {
        this.runawayCatViewModel = runawayCatViewModel;
        this.runawayCatInputBoundary = runawayCatInputBoundary;
    }

    /** Switches to the break session view
     *
     */
    public void switchToBreakView() {
        //To be implemented
    }
}