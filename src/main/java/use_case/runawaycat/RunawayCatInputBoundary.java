package use_case.runawaycat;

/**
 * The Input Boundary for runaway cat Use Case.
 */
public interface RunawayCatInputBoundary {

    /**
     * Executes the runaway cat use case.
     *
     * @param runawayCatInputData the input data of use case
     */
    void execute(RunawayCatInputData runawayCatInputData);

    /**
     * Executes the switch to Break Session view.
     */
    void switchToBreakView();
}
