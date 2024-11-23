package use_case.cat;

import data_access.InMemoryCatDataAccessObject;
import entity.Cat;
import entity.CatFactory;
import entity.FoodItemFactory;
import entity.WetFood;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.cat_management.change_cat_happiness.*;
import use_case.cat_management.change_cat_hunger.*;
import use_case.cat_management.create_cat.*;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.*;

class CatInteractorTest {

    @Test
    void successCreateCatTest() {
        final CreateCatInputData inputData = new CreateCatInputData("Billy", "<3");
        final InMemoryCatDataAccessObject catRepository = new InMemoryCatDataAccessObject();
        final CatFactory catFactory = new CatFactory();

        final CreateCatOutputBoundary successPresenter = new CreateCatOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateCatOutputData createCatOutputData) {
                assertTrue(createCatOutputData.isSuccess());
                assertEquals("Billy", createCatOutputData.getCatName());
                assertTrue(createCatOutputData.getCat().isCatObjectCreated());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }
        };

        final CreateCatInputBoundary interactor = new CreateCatInteractor(catRepository, successPresenter, catFactory);
        interactor.execute(inputData);
    }

    @Test
    void failureSameCatNameTest() {
        final CreateCatInputData inputData = new CreateCatInputData("Billy", "<3");
        final InMemoryCatDataAccessObject catRepository = new InMemoryCatDataAccessObject();
        final CatFactory catFactory = new CatFactory();
        final Cat existingCat = catFactory.create("Billy", "<3");
        existingCat.setCatObjectCreated(true);
        catRepository.saveCat(existingCat);

        final CreateCatOutputBoundary failurePresenter = new CreateCatOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateCatOutputData createCatOutputData) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertTrue(errorMessage.equals("You already have cat with this name >:("));
            }
        };

        final CreateCatInputBoundary interactor = new CreateCatInteractor(catRepository, failurePresenter, catFactory);
        interactor.execute(inputData);
    }

    @Test
    void failureMaxCatsTest() {
        final CreateCatInputData inputData = new CreateCatInputData("Billy", "<3");
        final InMemoryCatDataAccessObject catRepository = new InMemoryCatDataAccessObject();
        final CatFactory catFactory = new CatFactory();
        final String[] catNames = {"a", "b", "c", "d", "e"};

        for (String name: catNames) {
            Cat cat = catFactory.create(name, "<3");
            cat.setCatObjectCreated(true);
            catRepository.saveCat(cat);
        }

        final CreateCatOutputBoundary failurePresenter = new CreateCatOutputBoundary() {

            @Override
            public void prepareSuccessView(CreateCatOutputData createCatOutputData) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }
            @Override
            public void prepareFailView(String errorMessage) {
                assertTrue(errorMessage.equals("Reached maximum amount of cats :("));
            }
        };

        final CreateCatInputBoundary interactor = new CreateCatInteractor(catRepository, failurePresenter, catFactory);
        interactor.execute(inputData);
    }

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

    @Test
    void successIncreaseCatHungerTest() {
        final CatFactory catFactory = new CatFactory();
        final Cat cat = catFactory.create("Billy", "<3");
        cat.setCatObjectCreated(true);

        final FoodItemFactory foodItemFactory = new FoodItemFactory();
        final WetFood wetFood = (WetFood) foodItemFactory.create("tuna");

        final ChangeCatHungerInputData inputData = new ChangeCatHungerInputData(cat.getName(),
                cat.getOwnerUsername(), wetFood);
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
