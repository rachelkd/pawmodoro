package interface_adapter.study_session;

import use_case.studysession.StudySessionInputBoundary;

/**
 * Controller for Study Session use case.
 */
public class StudySessionController {
    private final StudySessionInputBoundary studySessionInteractor;

    public StudySessionController(StudySessionInputBoundary studySessionInteractor) {
        this.studySessionInteractor = studySessionInteractor;
    }

    /**
     * Executes the 'switch to set up session view' Use Case.
     */
    public void switchToSetupSessionView() {
        studySessionInteractor.switchToSetupSessionView();
    }

    /**
     * Executes the 'switch to log out' Use Case.
     */
    public void logout() {
        studySessionInteractor.logout();
    }
}
