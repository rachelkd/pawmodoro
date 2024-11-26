package use_case.display_cat_stats;

import entity.Cat;
import entity.exceptions.NoCatsFoundException;
import use_case.cat.CatDataAccessInterface;

/**
 * Interactor for displaying cat statistics.
 */
public class DisplayCatStatsInteractor implements DisplayCatStatsInputBoundary {
    private final CatDataAccessInterface catDataAccessObject;
    private final DisplayCatStatsOutputBoundary displayCatStatsPresenter;

    public DisplayCatStatsInteractor(CatDataAccessInterface catDataAccessObject,
            DisplayCatStatsOutputBoundary displayCatStatsPresenter) {
        this.catDataAccessObject = catDataAccessObject;
        this.displayCatStatsPresenter = displayCatStatsPresenter;
    }

    @Override
    public void execute(DisplayCatStatsInputData displayCatStatsInputData) {
        final String catName = displayCatStatsInputData.getCatName();
        final String username = displayCatStatsInputData.getUsername();

        final Cat cat = catDataAccessObject.getCatByNameAndOwner(catName, username);
        if (cat == null) {
            displayCatStatsPresenter.prepareFailView(new NoCatsFoundException(username).getMessage());
        }
        else {
            final DisplayCatStatsOutputData outputData = new DisplayCatStatsOutputData(
                    catName,
                    cat.getHungerLevel(),
                    cat.getHappinessLevel(),
                    cat.getImageFileName());

            displayCatStatsPresenter.prepareSuccessView(outputData);
        }
    }
}
