package interface_adapter.change_cat_hunger;

import entity.AbstractFood;
import entity.Cat;
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
     * @param cat
     * @param food the food object
     */
    void execute(String catName, String ownerUsername, Cat cat, AbstractFood food) {
        final ChangeCatHungerInputData changeCatHungerInputData =
                new ChangeCatHungerInputData(catName, ownerUsername, cat, food);

        changeCatHungerInteractor.execute(changeCatHungerInputData);
    }

    /**
     * Executes the Change Cat Hunger Use Case for when the cat gets more hungry.
     * @param catName
     * @param ownerUsername
     * @param cat
     * @param studySessionLength
     */
    void execute(String catName, String ownerUsername, Cat cat, int studySessionLength) {
        final ChangeCatHungerInputData changeCatHungerInputData =
                new ChangeCatHungerInputData(catName, ownerUsername, cat, studySessionLength);

        changeCatHungerInteractor.execute(changeCatHungerInputData);
    }
}
