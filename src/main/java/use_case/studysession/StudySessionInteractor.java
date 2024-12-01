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
        studySessionPresenter.prepareLoginView();
        studySessionPresenter.switchToLoginView();
    }

    @Override
    public void switchToBreakSessionView() {
        studySessionPresenter.switchToBreakSessionView();
    }

    @Override
    public void handle(StudySessionInputData studySessionInputData) {
        stopStudyTimer(studySessionInputData);
    }

    @Override
    public void stopStudyTimer(StudySessionInputData studySessionInputData) {
        final int studySessionInterval = studySessionInputData.getCurrentWorkInterval();
        final boolean studySessionIsSuccess = studySessionInputData.isTimerRunning();
        final StudySessionOutputData studySessionOutputData = new StudySessionOutputData(studySessionInterval,
                studySessionIsSuccess);
        studySessionPresenter.stopStudyTimer(studySessionOutputData);
    }
}
