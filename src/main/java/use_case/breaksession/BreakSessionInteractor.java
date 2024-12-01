package use_case.breaksession;

/**
 * Interactor for break session.
 */
public class BreakSessionInteractor implements BreakSessionInputBoundary {
    private final BreakSessionOutputBoundary breakSessionPresenter;

    public BreakSessionInteractor(BreakSessionOutputBoundary breakSessionPresenter) {
        this.breakSessionPresenter = breakSessionPresenter;
    }

    @Override
    public void switchToStudySessionView() {
        breakSessionPresenter.switchToStudySessionView();
    }

    @Override
    public void logout() {
        breakSessionPresenter.prepareLoginView();
        breakSessionPresenter.switchToLoginView();
    }
}
