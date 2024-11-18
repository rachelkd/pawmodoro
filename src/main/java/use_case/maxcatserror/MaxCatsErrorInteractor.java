package use_case.maxcatserror;

/**
 * The Max Cats Error Interactor
 */
public class MaxCatsErrorInteractor implements MaxCatsErrorInputBoundary {
    private final MaxCatsErrorOutputBoundary maxCatsPresenter;

    public MaxCatsErrorInteractor(MaxCatsErrorOutputBoundary maxCatsPresenter) {
        this.maxCatsPresenter = maxCatsPresenter;
    }

    @Override
    public void execute() {

    }



    @Override
    public void switchToBreakView() {

    }
}

