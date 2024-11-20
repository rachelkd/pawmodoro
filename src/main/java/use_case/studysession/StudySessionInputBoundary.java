package use_case.studysession;

/**
 * Input Boundary for actions which are related to study session.
 */
public interface StudySessionInputBoundary {

    /**
     * Executes the switch to setup session view.
     */
    void switchToSetupSessionView();

    /**
     * Logs out the user. Executes the switch to login view.
     */
    void logout();
}
