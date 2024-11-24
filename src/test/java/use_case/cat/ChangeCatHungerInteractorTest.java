package use_case.cat;

import org.junit.jupiter.api.Test;

import data_access.InMemoryCatDataAccessObject;
import entity.Cat;
import entity.CatFactory;
import entity.FoodItemFactory;
import entity.WetFood;
import use_case.cat_management.change_cat_hunger.ChangeCatHungerInputBoundary;
import use_case.cat_management.change_cat_hunger.ChangeCatHungerInputData;
import use_case.cat_management.change_cat_hunger.ChangeCatHungerInteractor;
import use_case.cat_management.change_cat_hunger.ChangeCatHungerOutputBoundary;
import use_case.cat_management.change_cat_hunger.ChangeCatHungerOutputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangeCatHungerInteractorTest {
    @Test
    void successIncreaseCatHungerTest() {
        final CatFactory catFactory = new CatFactory();
        final Cat cat = catFactory.create("Billy", "<3");
        cat.setCatObjectCreated(true);

        final FoodItemFactory foodItemFactory = new FoodItemFactory();
        final WetFood wetFood = (WetFood) foodItemFactory.create("tuna", "Tuna");

        final ChangeCatHungerInputData inputData = new ChangeCatHungerInputData(cat.getName(),
                cat.getOwnerUsername(), wetFood);
        final InMemoryCatDataAccessObject catRepository = new InMemoryCatDataAccessObject();
        cat.updateHungerLevel(-30);
        catRepository.saveCat(cat);

        final ChangeCatHungerOutputBoundary successPresenter = new ChangeCatHungerOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangeCatHungerOutputData changeCatHungerOutputData) {
                assertEquals(95, changeCatHungerOutputData.getNewHungerLevel());
            }
        };

        final ChangeCatHungerInputBoundary interactor = new ChangeCatHungerInteractor(catRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void successDecreaseCatHungerTest() {
        final CatFactory catFactory = new CatFactory();
        final Cat cat = catFactory.create("Billy", "<3");
        cat.setCatObjectCreated(true);

        final ChangeCatHungerInputData inputData = new ChangeCatHungerInputData(cat.getName(),
                cat.getOwnerUsername(), 20);
        final InMemoryCatDataAccessObject catRepository = new InMemoryCatDataAccessObject();
        catRepository.saveCat(cat);

        final ChangeCatHungerOutputBoundary successPresenter = new ChangeCatHungerOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangeCatHungerOutputData changeCatHungerOutputData) {
                assertEquals(90, changeCatHungerOutputData.getNewHungerLevel());
            }
        };

        final ChangeCatHungerInputBoundary interactor = new ChangeCatHungerInteractor(catRepository, successPresenter);
        interactor.execute(inputData);
    }
}
