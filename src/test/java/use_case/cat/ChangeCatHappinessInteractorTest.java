package use_case.cat;

import org.junit.jupiter.api.Test;

import data_access.InMemoryCatDataAccessObject;
import entity.Cat;
import entity.CatFactory;
import use_case.cat_management.change_cat_happiness.ChangeCatHappinessInputBoundary;
import use_case.cat_management.change_cat_happiness.ChangeCatHappinessInputData;
import use_case.cat_management.change_cat_happiness.ChangeCatHappinessInteractor;
import use_case.cat_management.change_cat_happiness.ChangeCatHappinessOutputBoundary;
import use_case.cat_management.change_cat_happiness.ChangeCatHappinessOutputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangeCatHappinessInteractorTest {

    @Test
    void successIncreaseCatHappinessTest() {
        final CatFactory catFactory = new CatFactory();
        final Cat cat = catFactory.create("Billy", "<3");
        cat.setCatObjectCreated(true);

        final ChangeCatHappinessInputData inputData = new ChangeCatHappinessInputData(cat.getName(),
                cat.getOwnerUsername(), true, 20);
        final InMemoryCatDataAccessObject catRepository = new InMemoryCatDataAccessObject();
        cat.updateHappinessLevel(-30);
        catRepository.saveCat(cat);

        final ChangeCatHappinessOutputBoundary successPresenter = new ChangeCatHappinessOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangeCatHappinessOutputData changeCatHappinessOutputData) {
                assertEquals(80, changeCatHappinessOutputData.getNewHappinessLevel());
            }
        };

        final ChangeCatHappinessInputBoundary interactor =
                new ChangeCatHappinessInteractor(catRepository, successPresenter);
        interactor.execute(inputData);

    }

    @Test
    void successDecreaseCatHappinessTest() {
        final CatFactory catFactory = new CatFactory();
        final Cat cat = catFactory.create("Billy", "<3");
        cat.setCatObjectCreated(true);

        final ChangeCatHappinessInputData inputData = new ChangeCatHappinessInputData(cat.getName(),
                cat.getOwnerUsername(), false, 59);
        final InMemoryCatDataAccessObject catRepository = new InMemoryCatDataAccessObject();
        cat.updateHappinessLevel(-10);
        catRepository.saveCat(cat);

        final ChangeCatHappinessOutputBoundary successPresenter = new ChangeCatHappinessOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangeCatHappinessOutputData changeCatHappinessOutputData) {
                assertEquals(55, changeCatHappinessOutputData.getNewHappinessLevel());
            }
        };

        final ChangeCatHappinessInputBoundary interactor =
                new ChangeCatHappinessInteractor(catRepository, successPresenter);
        interactor.execute(inputData);
    }
}
