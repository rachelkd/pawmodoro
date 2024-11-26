package use_case.setupsession;

/**
 * Interactor for Setup Session.
 */
public class SetupSessionInteractor implements SetupSessionInputBoundary {
    private final SetupSessionOutputBoundary setupPresenter;

    public SetupSessionInteractor(SetupSessionOutputBoundary setupPresenter) {
        this.setupPresenter = setupPresenter;
    }

    @Override
    public void execute(SetupSessionInputData setupSessionInputData) {
        final int studyTimeMinutes = setupSessionInputData.getStudyTime();
        final int breakTimeMinutes = setupSessionInputData.getBreakTime();
        final SetupSessionOutputData setupSessionOutputData = new SetupSessionOutputData(studyTimeMinutes,
                breakTimeMinutes);

        setupPresenter.prepareSuccessView(setupSessionOutputData);
    }

    @Override
    public void switchToStudyView() {
        setupPresenter.switchToStudyView();
    }

    @Override
    public void setupSession(int studyTime, int breakTime) {
        execute(new SetupSessionInputData(studyTime, breakTime));
    }

    @Override
    public void handle(SetupSessionInputData setupSessionInputData) {
        execute(setupSessionInputData);
    }
}

