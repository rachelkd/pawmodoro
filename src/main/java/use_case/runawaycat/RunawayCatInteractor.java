package use_case.runawaycat;

import entity.Cat;

/**
 * The Runaway Cat Interactor
 */

public class RunawayCatInteractor implements RunawayCatInputBoundary {
    private final RunawayCatOutputBoundary runawayPresenter;

    public RunawayCatInteractor(RunawayCatOutputBoundary runawayPresenter) {
        this.runawayPresenter = runawayPresenter;
    }

    public void execute() {
        runawayPresenter.prepareSuccessView();
    }

    @Override
    public void switchToBreakView() {
        runawayPresenter.switchToBreakView();
    }


}
