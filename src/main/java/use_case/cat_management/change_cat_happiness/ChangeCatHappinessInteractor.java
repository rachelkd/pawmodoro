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

        final ChangeCatHappinessOutputData changeCatHappinessOutputData = new ChangeCatHappinessOutputData(
                cat.getOwnerUsername(), cat.getName(), 
                catDataAccessObject.getHappinessLevel(cat.getName(), cat.getOwnerUsername()));
        changeCatHappinessPresenter.prepareSuccessView(changeCatHappinessOutputData);

    }

    int calculateHappinessPoints(int studySessionLength) {
        if (studySessionLength <= 20) {
            return Constants.POINTS_FOR_LESS_EQUAL_20;
        }
        else if (studySessionLength <= 40) {
            return Constants.POINTS_FOR_BETWEEN_21_AND_40;
        }
        else if (studySessionLength < 60) {
            return Constants.POINTS_FOR_BETWEEN_41_AND_59;
        }
        else {
            return Constants.POINTS_FOR_60;
        }
    }
}
