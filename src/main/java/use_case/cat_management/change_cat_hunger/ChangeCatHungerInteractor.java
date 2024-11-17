package use_case.cat_management.change_cat_hunger;

import use_case.cat_management.change_cat_happiness.ChangeCatHappinessInputBoundary;
import use_case.cat_management.change_cat_happiness.ChangeCatHappinessInputData;

/**
 * The Change Cat Hunger Use Case Interactor.
 */
public class ChangeCatHungerInteractor implements ChangeCatHappinessInputBoundary {
    private final ChangeCatHungerDataAccessInterface changeCatHungerDataAccessObject;
    private final ChangeCatHungerOutputBoundary changeCatHungerPresenter;

    public ChangeCatHungerInteractor(ChangeCatHungerDataAccessInterface changeCatHungerDataAccessObject, ChangeCatHungerOutputBoundary changeCatHungerPresenter) {
        this.changeCatHungerDataAccessObject = changeCatHungerDataAccessObject;
        this.changeCatHungerPresenter = changeCatHungerPresenter;
    }

    @Override
    public void execute(ChangeCatHappinessInputData changeCatHappinessInputData) {

    }
}
