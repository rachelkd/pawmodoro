package use_case.cat_management.change_cat_hunger;

import constants.Constants;
import entity.AbstractFood;
import entity.Cat;
import entity.FoodItemFactory;
import use_case.cat.CatDataAccessInterface;

/**
 * The Change Cat Hunger Use Case Interactor.
 */
public class ChangeCatHungerInteractor implements ChangeCatHungerInputBoundary {
    private final CatDataAccessInterface catDataAccessObject;
    private final ChangeCatHungerOutputBoundary changeCatHungerPresenter;

    public ChangeCatHungerInteractor(CatDataAccessInterface catDataAccessObject,
                                     ChangeCatHungerOutputBoundary changeCatHungerPresenter) {
        this.catDataAccessObject = catDataAccessObject;
        this.changeCatHungerPresenter = changeCatHungerPresenter;
    }

    @Override
    public void execute(ChangeCatHungerInputData changeCatHungerInputData) {

        if ("".equalsIgnoreCase(changeCatHungerInputData.getCatName())) {
            changeCatHungerPresenter.prepareFailView("You did not select a cat!");
        }
        else {
            // get the cat, cat object should already exist dues to create cat use case
            final Cat cat = catDataAccessObject.getCatByNameAndOwner(changeCatHungerInputData.getCatName(),
                    changeCatHungerInputData.getOwnerUsername());
            int hungerChange = 0;

            if (changeCatHungerInputData.getFoodName() != null) {
                final FoodItemFactory foodItemFactory = new FoodItemFactory();
                final AbstractFood food = foodItemFactory.create(changeCatHungerInputData.getFoodName());

                hungerChange += food.getPoints();
                cat.updateHungerLevel(hungerChange);
            }
            else {
                hungerChange -= hungerDecreaseCalculator(changeCatHungerInputData.getStudySessionLength());
                cat.updateHungerLevel(hungerChange);
            }

            // update the database
            catDataAccessObject.updateCat(cat);

            final ChangeCatHungerOutputData changeCatHungerOutputData = new ChangeCatHungerOutputData(
                    cat.getOwnerUsername(), cat.getName(),
                    catDataAccessObject.getHungerLevel(cat.getName(), cat.getOwnerUsername()));
            changeCatHungerPresenter.prepareSuccessView(changeCatHungerOutputData);
        }
    }

    int hungerDecreaseCalculator(int studySessionLength) {
        final int hungerPoints;
        if (studySessionLength <= Constants.MINUTES_20) {
            hungerPoints = Constants.HUNGER_FOR_LESS_EQUAL_20;
        }
        else if (studySessionLength <= Constants.MINUTES_40) {
            hungerPoints = Constants.HUNGER_FOR_BETWEEN_21_AND_40;
        }
        else if (studySessionLength < Constants.MINUTES_60) {
            hungerPoints = Constants.HUNGER_FOR_BETWEEN_41_AND_59;
        }
        else {
            hungerPoints = Constants.HUNGER_FOR_60;
        }
        return hungerPoints;
    }
}
