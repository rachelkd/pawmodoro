package interface_adapter.study_session;

import use_case.studysession.StudySessionInputBoundary;
import use_case.studysession.StudySessionInputData;

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

    /**
     * Executes the 'switch to break session view' Use Case.
     */
    public void switchToBreakSessionView() {
        studySessionInteractor.switchToBreakSessionView();
    }

    /**
     * Stops the timer for the study session.
     * @param studySession the current interval of study session.
     * @param isSuccess whether if user succeeded the pomodoro.
     */
    public void stopStudyTimer(int studySession, boolean isSuccess) {
        final StudySessionInputData studySessionInputData = new StudySessionInputData(studySession, isSuccess);
        studySessionInteractor.handle(studySessionInputData);
    }

}
