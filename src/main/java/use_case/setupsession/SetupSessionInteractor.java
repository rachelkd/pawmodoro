package use_case.setupsession;

/**
 * The Setup session interactor.
 *
 */
public class SetupSessionInteractor implements SetupSessionInputBoundary {
    private final SetupSessionOutputBoundary setupPresenter;

    public SetupSessionInteractor(SetupSessionOutputBoundary setupPresenter) {
        this.setupPresenter = setupPresenter;
    }

    @Override
    public void execute(SetupSessionInputData setupSessionInputData) {
        final SetupSessionOutputData setupSessionOutputData = new SetupSessionOutputData(setupSessionInputData
            .getStudyTime(), setupSessionInputData.getBreakTime());
        setupPresenter.prepareSuccessView(setupSessionOutputData);
    }

    @Override
    public void switchToStudyView() {
        setupPresenter.switchToStudyView();
    }
}
