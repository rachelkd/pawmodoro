package use_case.cat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data_access.InMemoryCatDataAccessObject;
import entity.Cat;
import entity.CatFactory;
import use_case.cat_management.create_cat.CreateCatInputBoundary;
import use_case.cat_management.create_cat.CreateCatInputData;
import use_case.cat_management.create_cat.CreateCatInteractor;
import use_case.cat_management.create_cat.CreateCatOutputBoundary;
import use_case.cat_management.create_cat.CreateCatOutputData;

class CreateCatInteractorTest {
    private CatFactory catFactory;

    @BeforeEach
    void setUp() {
        this.catFactory = new CatFactory();
    }

    @Test
    void successCreateCatTest() {
        final CreateCatInputData inputData = new CreateCatInputData("Billy", "<3");
        final InMemoryCatDataAccessObject catRepository = new InMemoryCatDataAccessObject();

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
                assertEquals("You already have cat with this name >:(", errorMessage);
            }
        };

        final CreateCatInputBoundary interactor = new CreateCatInteractor(catRepository, failurePresenter, catFactory);
        interactor.execute(inputData);
    }

    @Test
    void failureMaxCatsTest() {
        final CreateCatInputData inputData = new CreateCatInputData("Billy", "<3");
        final InMemoryCatDataAccessObject catRepository = new InMemoryCatDataAccessObject();
        final String[] catNames = {"a", "b", "c", "d", "e"};

        for (String name: catNames) {
            final Cat cat = catFactory.create(name, "<3");
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
                assertEquals("Reached maximum amount of cats :(", errorMessage);
            }
        };

        final CreateCatInputBoundary interactor = new CreateCatInteractor(catRepository, failurePresenter, catFactory);
        interactor.execute(inputData);
    }
}
