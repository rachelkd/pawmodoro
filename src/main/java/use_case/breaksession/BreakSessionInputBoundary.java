package use_case.breaksession;

/**
 * Input Boundary for actions which are related to study session.
 */
public interface BreakSessionInputBoundary {

    /**
     * Executes the switch to study session view.
     */
    void switchToSetupSessionView();

    /**
     * Logs out the user. Executes the switch to login view.
     */
    void logout();
}
