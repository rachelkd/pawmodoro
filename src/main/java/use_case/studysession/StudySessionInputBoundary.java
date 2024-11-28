package use_case.studysession;

/**
 * Input Boundary for actions which are related to study session.
 */
public interface StudySessionInputBoundary {

    /**
     * Executes the switch to set up session view.
     */
    void switchToSetupSessionView();

    /**
     * Logs out the user. Executes the switch to login view.
     */
    void logout();

    /**
     * Executes the switch to break session view.
     */
    void switchToBreakSessionView();

    /**
     * stopStudyTimer
     */
    void stopStudyTimer();
}
