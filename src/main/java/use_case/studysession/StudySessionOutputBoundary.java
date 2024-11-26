package use_case.studysession;

/**
 * Output boundary for the study session use case.
 */
public interface StudySessionOutputBoundary {
    /**
     * Switches the view to the setup session view.
     * Called when the user needs to configure a new study session.
     */
    void switchToSetupSessionView();

    /**
     * Switches the view to the login view.
     * Called when the user logs out of their study session.
     */
    void switchToLoginView();

    /**
     * Prepares the login view.
     * Called when the login view needs to be prepared.
     */
    void prepareLoginView();

    /**
     * Switches the view to the break session view.
     * Called when the user goes on break after completing the work interval.
     */
    void switchToBreakSessionView();
}
