package use_case.cat_management.change_cat_hunger;

import entity.AbstractFood;
import entity.Cat;
import entity.FoodItemFactory;
import entity.HungerCalculator;
import use_case.cat.CatDataAccessInterface;

/**
 * The Change Cat Hunger Use Case Interactor.
 */
public class ChangeCatHungerInteractor implements ChangeCatHungerInputBoundary {
    private final CatDataAccessInterface catDataAccessObject;
    private final ChangeCatHungerOutputBoundary changeCatHungerPresenter;
    private final HungerCalculator hungerCalculator;
    private final FoodItemFactory foodItemFactory;

    public ChangeCatHungerInteractor(CatDataAccessInterface catDataAccessObject,
                                     ChangeCatHungerOutputBoundary changeCatHungerPresenter,
                                     HungerCalculator hungerCalculator,
                                     FoodItemFactory foodItemFactory) {
        this.catDataAccessObject = catDataAccessObject;
        this.changeCatHungerPresenter = changeCatHungerPresenter;
        this.hungerCalculator = hungerCalculator;
        this.foodItemFactory = foodItemFactory;
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
                final AbstractFood food = foodItemFactory.create(changeCatHungerInputData.getFoodName());

                hungerChange += food.getPoints();
                cat.updateHungerLevel(hungerChange);
            }
            else {
                hungerChange -=
                        hungerCalculator.calculateHungerPoints(changeCatHungerInputData.getStudySessionLength());
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
}
