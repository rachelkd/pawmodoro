package use_case.cat_management.change_cat_happiness;

import constants.Constants;
import entity.Cat;
import use_case.cat.CatDataAccessInterface;

/**
 * The Change Cat Happiness Use Case Interactor.
 */
public class ChangeCatHappinessInteractor implements ChangeCatHappinessInputBoundary {
    private final CatDataAccessInterface catDataAccessObject;
    private final ChangeCatHappinessOutputBoundary changeCatHappinessPresenter;

    public ChangeCatHappinessInteractor(CatDataAccessInterface catDataAccessObject,
                                        ChangeCatHappinessOutputBoundary changeCatHappinessPresenter) {
        this.catDataAccessObject = catDataAccessObject;
        this.changeCatHappinessPresenter = changeCatHappinessPresenter;
    }

    @Override
    public void execute(ChangeCatHappinessInputData changeCatHappinessInputData) {
        // get the cat, cat should already exist
        final Cat cat = catDataAccessObject.getCatByNameAndOwner(changeCatHappinessInputData.getCatName(),
                changeCatHappinessInputData.getOwnerUsername());
        int newHappiness = 0;

        // decrease happiness when user does not complete study session
        if (!changeCatHappinessInputData.isCompletedStudySession()) {
            newHappiness -= calculateHappinessPoints(changeCatHappinessInputData.getStudySessionLength());
        }
        // increase happiness when complete study session
        else {
            newHappiness += calculateHappinessPoints(changeCatHappinessInputData.getStudySessionLength());
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

    int calculateHappinessPoints(int studySessionLength) {
        final int happinessPoints;
        if (studySessionLength <= Constants.MINUTES_20) {
            happinessPoints = Constants.POINTS_FOR_LESS_EQUAL_20;
        }
        else if (studySessionLength <= Constants.MINUTES_40) {
            happinessPoints = Constants.POINTS_FOR_BETWEEN_21_AND_40;
        }
        else if (studySessionLength < Constants.MINUTES_60) {
            happinessPoints = Constants.POINTS_FOR_BETWEEN_41_AND_59;
        }
        else {
            happinessPoints = Constants.POINTS_FOR_60;
        }
        return happinessPoints;
    }
}
