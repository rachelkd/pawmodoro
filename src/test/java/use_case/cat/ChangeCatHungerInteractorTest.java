package use_case.cat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import data_access.InMemoryCatDataAccessObject;
import entity.Cat;
import entity.CatFactory;
import use_case.cat_management.change_cat_hunger.ChangeCatHungerInputBoundary;
import use_case.cat_management.change_cat_hunger.ChangeCatHungerInputData;
import use_case.cat_management.change_cat_hunger.ChangeCatHungerInteractor;
import use_case.cat_management.change_cat_hunger.ChangeCatHungerOutputBoundary;
import use_case.cat_management.change_cat_hunger.ChangeCatHungerOutputData;

class ChangeCatHungerInteractorTest {
    @Test
    void successIncreaseCatHungerTest() {
        final CatFactory catFactory = new CatFactory();
        final Cat cat = catFactory.create("Billy", "<3");
        cat.setCatObjectCreated(true);

        //

        final ChangeCatHungerInputData inputData = new ChangeCatHungerInputData(cat.getName(),
                cat.getOwnerUsername(), "tuna");
        final InMemoryCatDataAccessObject catRepository = new InMemoryCatDataAccessObject();
        cat.updateHungerLevel(-30);
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
