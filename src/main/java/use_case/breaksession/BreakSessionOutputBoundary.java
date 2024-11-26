package use_case.breaksession;

/**
 * Output boundary for the study session use case.
 */
public interface BreakSessionOutputBoundary {
    /**
     * Switches the view to the study session view.
     * Called when the user is done with their break time.
     */
    void switchToStudySessionView();

    /**
     * Switches the view to the login view.
     * Called when the user logs out of their break session.
     */
    void switchToLoginView();

    /**
     * Prepares the login view.
     * Called when the login view needs to be prepared.
     */
    void prepareLoginView();

}
