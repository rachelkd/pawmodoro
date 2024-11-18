package interface_adapter.change_cat_hunger;

import entity.AbstractFood;
import entity.Cat;
import use_case.cat_management.change_cat_happiness.ChangeCatHappinessInputBoundary;
import use_case.cat_management.change_cat_hunger.ChangeCatHungerInputBoundary;
import use_case.cat_management.change_cat_hunger.ChangeCatHungerInputData;

public class ChangeCatHungerController {
    private final ChangeCatHungerInputBoundary changeCatHungerInteractor;

    public ChangeCatHungerController(ChangeCatHungerInputBoundary changeCatHungerInteractor) {
        this.changeCatHungerInteractor = changeCatHungerInteractor;
    }

    void execute(String catName, String ownerUsername, Cat cat, AbstractFood food) {
        final ChangeCatHungerInputData changeCatHungerInputData =
                new ChangeCatHungerInputData(catName, ownerUsername, cat, food);

        changeCatHungerInteractor.execute(changeCatHungerInputData);
    }

    void execute(String catName, String ownerUsername, Cat cat, int studySessionLength) {
        final ChangeCatHungerInputData changeCatHungerInputData =
                new ChangeCatHungerInputData(catName, ownerUsername, cat, studySessionLength);

        changeCatHungerInteractor.execute(changeCatHungerInputData);
    }
}
