package use_case.runawaycat;

/**
 * The output boundary for the runaway cats use case
 */
public interface RunawayCatOutputBoundary {

    /**
     * After the user presses button to confirm they saw the message.
     */
    void prepareSuccessView();

    /**
     * To return to the break session use case
     */
    void switchToBreakView();

}
