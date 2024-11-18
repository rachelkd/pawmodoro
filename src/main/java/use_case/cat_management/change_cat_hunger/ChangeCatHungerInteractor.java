package use_case.cat_management.change_cat_hunger;

import constants.Constants;
import entity.AbstractFood;
import entity.Cat;

/**
 * The Change Cat Hunger Use Case Interactor.
 */
public class ChangeCatHungerInteractor implements ChangeCatHungerInputBoundary {
    private final ChangeCatHungerDataAccessInterface changeCatHungerDataAccessObject;
    private final ChangeCatHungerOutputBoundary changeCatHungerPresenter;

    public ChangeCatHungerInteractor(ChangeCatHungerDataAccessInterface changeCatHungerDataAccessObject, ChangeCatHungerOutputBoundary changeCatHungerPresenter) {
        this.changeCatHungerDataAccessObject = changeCatHungerDataAccessObject;
        this.changeCatHungerPresenter = changeCatHungerPresenter;
    }

    @Override
    public void execute(ChangeCatHungerInputData changeCatHungerInputData) {
        // get the cat, cat object should already exist dues to create cat use case
        final Cat cat = changeCatHungerInputData.getCat();
        int hungerChange = 0;

        if (changeCatHungerInputData.getFood() != null) {
            final AbstractFood food = changeCatHungerInputData.getFood();

            hungerChange += food.getPoints();
            cat.updateHungerLevel(hungerChange);
        }
        else {
            hungerChange -= hungerDecreaseCalculator(changeCatHungerInputData.getStudySessionLength());
            cat.updateHungerLevel(hungerChange);
        }

        // update the database
        changeCatHungerDataAccessObject.updateCat(cat);

        final ChangeCatHungerOutputData changeCatHungerOutputData = new ChangeCatHungerOutputData(
                cat.getOwnerUsername(), cat,
                changeCatHungerDataAccessObject.getHungerLevel(cat.getName(), cat.getOwnerUsername()));
        changeCatHungerPresenter.prepareSuccessView(changeCatHungerOutputData);

    }

    int hungerDecreaseCalculator(int studySessionLength) {
        if (studySessionLength <= 20) {
            return Constants.HUNGER_FOR_LESS_EQUAL_20;
        }
        else if (studySessionLength <= 40) {
            return Constants.HUNGER_FOR_BETWEEN_21_AND_40;
        }
        else if (studySessionLength < 60) {
            return Constants.HUNGER_FOR_BETWEEN_41_AND_59;
        }
        else {
            return Constants.HUNGER_FOR_60;
        }
    }
}
