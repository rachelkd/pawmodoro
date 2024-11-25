package interface_adapter.change_cat_hunger;

import use_case.cat_management.change_cat_hunger.ChangeCatHungerInputBoundary;
import use_case.cat_management.change_cat_hunger.ChangeCatHungerInputData;

/**
 * The Controller for the Change Cat Hunger Use Case.
 */
public class ChangeCatHungerController {
    private final ChangeCatHungerInputBoundary changeCatHungerInteractor;

    public ChangeCatHungerController(ChangeCatHungerInputBoundary changeCatHungerInteractor) {
        this.changeCatHungerInteractor = changeCatHungerInteractor;
    }

    /**
     * Executes the Change Cat Hunger Use Case for when the cat is feed.
     * @param catName the name of the cat
     * @param ownerUsername the cat owner's username
     * @param foodName the food object
     */
    public void execute(String catName, String ownerUsername, String foodName) {
        final ChangeCatHungerInputData changeCatHungerInputData =
                new ChangeCatHungerInputData(catName, ownerUsername, foodName);

        changeCatHungerInteractor.execute(changeCatHungerInputData);
    }

    /**
     * Executes the Change Cat Hunger Use Case for when the cat gets more hungry.
     * @param catName cat name
     * @param ownerUsername cat owner's username
     * @param studySessionLength length of study session
     */
    public void execute(String catName, String ownerUsername, int studySessionLength) {
        final ChangeCatHungerInputData changeCatHungerInputData =
                new ChangeCatHungerInputData(catName, ownerUsername, studySessionLength);

        changeCatHungerInteractor.execute(changeCatHungerInputData);
    }
}
