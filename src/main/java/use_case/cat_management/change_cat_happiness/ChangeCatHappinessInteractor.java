package use_case.cat_management.change_cat_happiness;

import constants.Constants;
import entity.Cat;

public class ChangeCatHappinessInteractor implements ChangeCatHappinessInputBoundary{
    private final ChangeCatHappinessDataAccessInterface changeCatHappinessDataAccessObject;
    private final ChangeCatHappinessOutputBoundary changeCatHappinessPresenter;

    public ChangeCatHappinessInteractor(ChangeCatHappinessDataAccessInterface changeCatHappinessDataAccessObject, ChangeCatHappinessOutputBoundary changeCatHappinessPresenter) {
        this.changeCatHappinessDataAccessObject = changeCatHappinessDataAccessObject;
        this.changeCatHappinessPresenter = changeCatHappinessPresenter;
    }

    @Override
    public void execute(ChangeCatHappinessInputData changeCatHappinessInputData) {
        // get the cat
        final Cat cat = changeCatHappinessDataAccessObject.getCatByNameAndOwnerId(changeCatHappinessInputData.getCatName(), changeCatHappinessInputData.getOwnerId());
        int newHappiness = cat.getHappinessLevel();

        // decrease happiness when user does not complete study session
        if (changeCatHappinessInputData.isCompletedStudySession()) {}

        // increase happiness when complete study session

        final ChangeCatHappinessOutputData changeCatHappinessOutputData = new ChangeCatHappinessOutputData(
                cat.getOwnerId(), cat.getName(), newHappiness);
        changeCatHappinessPresenter.prepareSuccessView(changeCatHappinessOutputData);

    }
}
