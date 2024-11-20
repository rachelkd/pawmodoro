package use_case.runawaycat;

/**
 * The Input Boundary for runaway cat Use Case.
 */
public interface RunawayCatInputBoundary {

    /**
     * Executes the runaway cat use case.
     */
    void execute();

    /**
     * Executes the switch to Break Session view.
     */
    void switchToBreakView();
}
