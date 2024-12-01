package use_case.cat_management.change_cat_happiness;

import entity.Cat;
import entity.HappinessCalculator;
import use_case.cat.CatDataAccessInterface;

/**
 * The Change Cat Happiness Use Case Interactor.
 */
public class ChangeCatHappinessInteractor implements ChangeCatHappinessInputBoundary {
    private final CatDataAccessInterface catDataAccessObject;
    private final ChangeCatHappinessOutputBoundary changeCatHappinessPresenter;
    private final HappinessCalculator happinessCalculator;

    public ChangeCatHappinessInteractor(CatDataAccessInterface catDataAccessObject,
                                        ChangeCatHappinessOutputBoundary changeCatHappinessPresenter,
                                        HappinessCalculator happinessCalculator) {
        this.catDataAccessObject = catDataAccessObject;
        this.changeCatHappinessPresenter = changeCatHappinessPresenter;
        this.happinessCalculator = happinessCalculator;
    }

    @Override
    public void execute(ChangeCatHappinessInputData changeCatHappinessInputData) {

        if ("".equalsIgnoreCase(changeCatHappinessInputData.getCatName())) {
            changeCatHappinessPresenter.prepareFailureView("You didn't to select a cat!");
        }
        else {
            // get the cat, cat should already exist
            final Cat cat = catDataAccessObject.getCatByNameAndOwner(changeCatHappinessInputData.getCatName(),
                    changeCatHappinessInputData.getOwnerUsername());
            int newHappiness = 0;

            // decrease happiness when user does not complete study session
            if (!changeCatHappinessInputData.isCompletedStudySession()) {
                newHappiness -= happinessCalculator
                        .calculateHappinessPoints(changeCatHappinessInputData.getStudySessionLength());
            }
            // increase happiness when complete study session
            else {
                newHappiness += happinessCalculator
                        .calculateHappinessPoints(changeCatHappinessInputData.getStudySessionLength());
            }
            cat.updateHappinessLevel(newHappiness);
            catDataAccessObject.updateCat(cat);

            if (cat.getHappinessLevel() <= 0) {
                catDataAccessObject.removeCat(cat.getName(), cat.getOwnerUsername());
                changeCatHappinessPresenter.switchToRunawayCatView(cat.getName(), cat.getOwnerUsername());
            }
            else {

                final ChangeCatHappinessOutputData changeCatHappinessOutputData = new ChangeCatHappinessOutputData(
                        cat.getOwnerUsername(), cat.getName(),
                        catDataAccessObject.getHappinessLevel(cat.getName(), cat.getOwnerUsername()));

                changeCatHappinessPresenter.prepareSuccessView(changeCatHappinessOutputData);
            }
        }
    }
}
