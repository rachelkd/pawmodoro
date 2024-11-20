package use_case.studysession;

/**
 * The study session interactor.
 */
public class StudySessionInteractor implements StudySessionInputBoundary {
    private final StudySessionOutputBoundary studySessionPresenter;

    public StudySessionInteractor(StudySessionOutputBoundary studySessionPresenter) {
        this.studySessionPresenter = studySessionPresenter;
    }

    @Override
    public void switchToSetupSessionView() {
        studySessionPresenter.switchToSetupSessionView();
    }

    @Override
    public void logout() {
        studySessionPresenter.switchToLoginView();
    }
}
