package interface_adapter.runawaycat;

import use_case.runawaycat.RunawayCatInputBoundary;

/**
 * The controller for the runaway cat view.
 */
public class RunawayCatController {
    private final RunawayCatViewModel runawayCatViewModel;
    private final RunawayCatInputBoundary runawayCatInputBoundary;

    public RunawayCatController(RunawayCatViewModel runawayCatViewModel,
            RunawayCatInputBoundary runawayCatInputBoundary) {
        this.runawayCatViewModel = runawayCatViewModel;
        this.runawayCatInputBoundary = runawayCatInputBoundary;
    }

    /**
     * Switches to the break session view.
     */
    public void switchToBreakView() {
        // To be implemented
    }
}
