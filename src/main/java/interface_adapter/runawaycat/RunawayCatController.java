package interface_adapter.runawaycat;

import use_case.runawaycat.RunawayCatInputBoundary;
import use_case.runawaycat.RunawayCatInputData;

/**
 * The controller for the runaway cat view.
 */
public class RunawayCatController {
    private final RunawayCatViewModel runawayCatViewModel;
    private final RunawayCatInputBoundary runawayCatInteractor;

    public RunawayCatController(RunawayCatViewModel runawayCatViewModel,
            RunawayCatInputBoundary runawayCatInteractor) {
        this.runawayCatViewModel = runawayCatViewModel;
        this.runawayCatInteractor = runawayCatInteractor;
    }

    /**
     * Executes the runaway cat use case.
     * @param catName the name of the cat
     * @param ownerUsername the username of the owner
     */
    public void execute(String catName, String ownerUsername) {
        final RunawayCatInputData runawayCatInputData = new RunawayCatInputData(catName, ownerUsername);
        runawayCatInteractor.execute(runawayCatInputData);
    }
}
