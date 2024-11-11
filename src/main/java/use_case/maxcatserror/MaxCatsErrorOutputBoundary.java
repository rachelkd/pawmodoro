package use_case.maxcatserror;

/**
 * Output boundary interface for the MaxCatsError use case.
 * This interface defines the methods that the presenter must implement
 * to handle the output from the MaxCatsError use case.
 */
public interface MaxCatsErrorOutputBoundary {
    /**
     * Called when the error has been acknowledged and the system should
     * prepare to return to the previous view.
     */
    void prepareSuccessView();

    /**
     * Called when the system needs to switch back to the setup view.
     */
    void switchToSetupView();
} 