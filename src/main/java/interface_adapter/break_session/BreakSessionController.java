package interface_adapter.break_session;

import use_case.breaksession.BreakSessionInputBoundary;

/**
 * Controller for Break Session use case.
 */
public class BreakSessionController {
    private final BreakSessionInputBoundary breakSessionInteractor;

    public BreakSessionController(BreakSessionInputBoundary breakSessionInteractor) {
        this.breakSessionInteractor = breakSessionInteractor;
    }

    /**
     * Executes the 'switch to study session view' Use Case.
     */
    public void switchToStudySessionView() {
        breakSessionInteractor.switchToStudySessionView();
    }

    /**
     * Executes the 'switch to log out' Use Case.
     */
    public void logout() {
        breakSessionInteractor.logout();
    }
}
