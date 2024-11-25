package use_case.runawaycat;

import entity.Cat;
import use_case.cat.CatDataAccessInterface;

/**
 * The Runaway Cat Interactor
 */

public class RunawayCatInteractor implements RunawayCatInputBoundary {
    private final RunawayCatOutputBoundary runawayPresenter;
    private final CatDataAccessInterface catDataAccessObject;

    public RunawayCatInteractor(RunawayCatOutputBoundary runawayPresenter,
                                CatDataAccessInterface catDataAccessObject) {
        this.runawayPresenter = runawayPresenter;
        this.catDataAccessObject = catDataAccessObject;
    }

    @Override
    public void execute(RunawayCatInputData runawayCatInputData) {
        catDataAccessObject.removeCat(runawayCatInputData.getCatName(), runawayCatInputData.getOwnerName());
        runawayPresenter.prepareSuccessView();
    }

    @Override
    public void switchToBreakView() {
        runawayPresenter.switchToBreakView();
    }


}
