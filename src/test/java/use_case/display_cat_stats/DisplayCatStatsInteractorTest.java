package use_case.display_cat_stats;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import data_access.InMemoryCatDataAccessObject;
import entity.Cat;
import entity.CatFactory;
import use_case.cat.CatDataAccessInterface;

class DisplayCatStatsInteractorTest {

    @Test
    void successTest() {
        final CatDataAccessInterface catRepository = new InMemoryCatDataAccessObject();
        final CatFactory catFactory = new CatFactory();
        final Cat cat = catFactory.create("Whiskers", "testuser");
        catRepository.saveCat(cat);
        cat.updateHungerLevel(-25);
        cat.updateHappinessLevel(-15);
        catRepository.updateCat(cat);

        final DisplayCatStatsOutputBoundary successPresenter = new DisplayCatStatsOutputBoundary() {
            @Override
            public void prepareSuccessView(DisplayCatStatsOutputData displayCatStatsOutputData) {
                assertEquals("Whiskers", displayCatStatsOutputData.getCatName());
                assertEquals(75, displayCatStatsOutputData.getHungerLevel());
                assertEquals(85, displayCatStatsOutputData.getHappinessLevel());
                assertEquals(cat.getImageFileName(), displayCatStatsOutputData.getImageFileName());
                assertFalse(displayCatStatsOutputData.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        final DisplayCatStatsInputBoundary interactor = new DisplayCatStatsInteractor(
                catRepository, successPresenter);

        final DisplayCatStatsInputData inputData = new DisplayCatStatsInputData("testuser", "Whiskers");
        interactor.execute(inputData);
    }

    @Test
    void failureNoCatFoundTest() {
        final CatDataAccessInterface catRepository = new InMemoryCatDataAccessObject();

        final DisplayCatStatsOutputBoundary failurePresenter = new DisplayCatStatsOutputBoundary() {
            @Override
            public void prepareSuccessView(DisplayCatStatsOutputData displayCatStatsOutputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("No cats found for user: testuser", error);
            }
        };

        final DisplayCatStatsInputBoundary interactor = new DisplayCatStatsInteractor(
                catRepository, failurePresenter);

        final DisplayCatStatsInputData inputData = new DisplayCatStatsInputData("testuser", "NonexistentCat");
        interactor.execute(inputData);
    }
}
