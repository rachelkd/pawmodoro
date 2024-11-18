package use_case.studysession;

/**
 * Input Boundary for actions which are related to study session.
 */
public interface StudySessionInputBoundary {

    /**
     * Executes the switch to SetupSession view.
     */
    void switchToSetupSessionView();

    /**
     * Executes the switch to logout view.
     */
    void switchToLogOutView();
}
