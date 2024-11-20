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
}
