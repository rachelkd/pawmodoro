package use_case.cat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data_access.InMemoryCatDataAccessObject;
import entity.Cat;
import entity.CatFactory;
import entity.DefaultHappinessCalculator;
import entity.HappinessCalculator;
import use_case.cat_management.change_cat_happiness.ChangeCatHappinessInputBoundary;
import use_case.cat_management.change_cat_happiness.ChangeCatHappinessInputData;
import use_case.cat_management.change_cat_happiness.ChangeCatHappinessInteractor;
import use_case.cat_management.change_cat_happiness.ChangeCatHappinessOutputBoundary;
import use_case.cat_management.change_cat_happiness.ChangeCatHappinessOutputData;

class ChangeCatHappinessInteractorTest {
    private CatFactory catFactory;
    private Cat cat;
    private InMemoryCatDataAccessObject catRepository;
    private HappinessCalculator happinessCalculator;

    @BeforeEach
    void setUp() {
        this.catFactory = new CatFactory();
        this.happinessCalculator = new DefaultHappinessCalculator();
        this.catRepository = new InMemoryCatDataAccessObject();
        cat = catFactory.create("Billy", "<3");
    }

    @Test
    void successIncreaseCatHappinessTest() {

        final ChangeCatHappinessInputData inputData = new ChangeCatHappinessInputData(cat.getName(),
                cat.getOwnerUsername(), true, 20);
        cat.updateHappinessLevel(-30);
        catRepository.saveCat(cat);

        final ChangeCatHappinessOutputBoundary successPresenter = new ChangeCatHappinessOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangeCatHappinessOutputData changeCatHappinessOutputData) {
                assertEquals(80, changeCatHappinessOutputData.getNewHappinessLevel());
            }

            @Override
            public void switchToRunawayCatView(String catName, String ownerUsername) {
                fail("Use case switch to runaway cat view is unexpected.");
            }

            @Override
            public void prepareFailureView(String errorMessage) {
                fail("Usecase unexpected");
            }
        };

        final ChangeCatHappinessInputBoundary interactor =
                new ChangeCatHappinessInteractor(catRepository, successPresenter, happinessCalculator);
        interactor.execute(inputData);

    }

    @Test
    void successDecreaseCatHappinessTest() {

        final ChangeCatHappinessInputData inputData = new ChangeCatHappinessInputData(cat.getName(),
                cat.getOwnerUsername(), false, 59);
        cat.updateHappinessLevel(-10);
        catRepository.saveCat(cat);

        final ChangeCatHappinessOutputBoundary successPresenter = new ChangeCatHappinessOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangeCatHappinessOutputData changeCatHappinessOutputData) {
                assertEquals(55, changeCatHappinessOutputData.getNewHappinessLevel());
            }

            @Override
            public void switchToRunawayCatView(String catName, String ownerUsername) {
                fail("Use case switch to runaway cat view is unexpected.");
            }

            @Override
            public void prepareFailureView(String errorMessage) {
                fail("Usecase unexpected");
            }
        };

        final ChangeCatHappinessInputBoundary interactor =
                new ChangeCatHappinessInteractor(catRepository, successPresenter, happinessCalculator);
        interactor.execute(inputData);
    }

    @Test
    void successSwitchToRunawayCatViewTest() {

        cat.updateHappinessLevel(-90);
        catRepository.saveCat(cat);
        final ChangeCatHappinessInputData inputData = new ChangeCatHappinessInputData(cat.getName(),
                cat.getOwnerUsername(), false, 60);

        final ChangeCatHappinessOutputBoundary runawayCatPresenter = new ChangeCatHappinessOutputBoundary() {

            @Override
            public void prepareSuccessView(ChangeCatHappinessOutputData changeCatHappinessOutputData) {
                fail("Use case is unexpected.");
            }

            @Override
            public void switchToRunawayCatView(String catName, String ownerUsername) {
                // checks if cat is removed
                assertFalse(catRepository.existsByNameAndOwner(catName, ownerUsername));
            }

            @Override
            public void prepareFailureView(String errorMessage) {
                fail("Usecase unexpected");
            }
        };

        final ChangeCatHappinessInputBoundary interactor =
                new ChangeCatHappinessInteractor(catRepository, runawayCatPresenter, happinessCalculator);
        interactor.execute(inputData);

    }

    @Test
    void failureChangeCatHappinessTest() {
        final ChangeCatHappinessInputData inputData = new ChangeCatHappinessInputData("",
                cat.getOwnerUsername(), false, 60);

        final ChangeCatHappinessOutputBoundary failurePresenter = new ChangeCatHappinessOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangeCatHappinessOutputData changeCatHappinessOutputData) {
                fail("Usecase unexpected");
            }

            @Override
            public void switchToRunawayCatView(String catName, String ownerUsername) {
                fail("Usecase unexpected");
            }

            @Override
            public void prepareFailureView(String errorMessage) {
                assertEquals("You didn't to select a cat!", errorMessage);
            }
        };
        final ChangeCatHappinessInputBoundary interactor =
                new ChangeCatHappinessInteractor(catRepository, failurePresenter, happinessCalculator);
        interactor.execute(inputData);
    }
}
