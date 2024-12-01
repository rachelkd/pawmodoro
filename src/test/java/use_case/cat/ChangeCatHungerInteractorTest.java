package use_case.cat;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data_access.InMemoryCatDataAccessObject;
import entity.Cat;
import entity.CatFactory;
import entity.DefaultHungerCalculator;
import entity.FoodItemFactory;
import entity.HungerCalculator;
import use_case.cat_management.change_cat_hunger.ChangeCatHungerInputBoundary;
import use_case.cat_management.change_cat_hunger.ChangeCatHungerInputData;
import use_case.cat_management.change_cat_hunger.ChangeCatHungerInteractor;
import use_case.cat_management.change_cat_hunger.ChangeCatHungerOutputBoundary;
import use_case.cat_management.change_cat_hunger.ChangeCatHungerOutputData;

class ChangeCatHungerInteractorTest {
    private CatFactory catFactory;
    private FoodItemFactory foodItemFactory;
    private InMemoryCatDataAccessObject catRepository;
    private HungerCalculator hungerCalculator;

    @BeforeEach
    void setUp() {
        this.catFactory = new CatFactory();
        this.catRepository = new InMemoryCatDataAccessObject();
        this.hungerCalculator = new DefaultHungerCalculator();
        this.foodItemFactory = new FoodItemFactory();
    }

    @Test
    void successIncreaseCatHungerTest() {
        final Cat cat = catFactory.create("Billy", "<3");

        final ChangeCatHungerInputData inputData = new ChangeCatHungerInputData(cat.getName(),
                cat.getOwnerUsername(), "tuna");

        cat.updateHungerLevel(-30);
        catRepository.saveCat(cat);

        final ChangeCatHungerOutputBoundary successPresenter = new ChangeCatHungerOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangeCatHungerOutputData changeCatHungerOutputData) {
                assertEquals(90, changeCatHungerOutputData.getNewHungerLevel());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Usecase unexpected.");
            }
        };

        final ChangeCatHungerInputBoundary interactor =
                new ChangeCatHungerInteractor(catRepository, successPresenter, hungerCalculator, foodItemFactory);
        interactor.execute(inputData);
    }

    @Test
    void successDecreaseCatHungerTest() {
        final Cat cat = catFactory.create("Billy", "<3");

        final ChangeCatHungerInputData inputData = new ChangeCatHungerInputData(cat.getName(),
                cat.getOwnerUsername(), 20);
        catRepository.saveCat(cat);

        final ChangeCatHungerOutputBoundary successPresenter = new ChangeCatHungerOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangeCatHungerOutputData changeCatHungerOutputData) {
                assertEquals(90, changeCatHungerOutputData.getNewHungerLevel());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Usecase unexpected");
            }
        };

        final ChangeCatHungerInputBoundary interactor =
                new ChangeCatHungerInteractor(catRepository, successPresenter, hungerCalculator, foodItemFactory);
        interactor.execute(inputData);
    }

    @Test
    void failChangeCatHungerTest() {
        final ChangeCatHungerInputData inputData =
                new ChangeCatHungerInputData("", "<3", 20);

        final ChangeCatHungerOutputBoundary failurePresenter = new ChangeCatHungerOutputBoundary() {

            @Override
            public void prepareSuccessView(ChangeCatHungerOutputData changeCatHungerOutputData) {
                fail("Usecase unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("You did not select a cat!", errorMessage);
            }
        };
        final ChangeCatHungerInputBoundary interactor =
                new ChangeCatHungerInteractor(catRepository, failurePresenter, hungerCalculator, foodItemFactory);
        interactor.execute(inputData);
    }
}
