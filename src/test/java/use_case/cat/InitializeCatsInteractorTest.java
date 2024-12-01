package use_case.cat;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data_access.InMemoryCatDataAccessObject;
import entity.Cat;
import entity.CatFactory;
import use_case.cat_management.initialize_cats.InitializeCatsInputBoundary;
import use_case.cat_management.initialize_cats.InitializeCatsInputData;
import use_case.cat_management.initialize_cats.InitializeCatsInteractor;
import use_case.cat_management.initialize_cats.InitializeCatsOutputBoundary;
import use_case.cat_management.initialize_cats.InitializeCatsOutputData;

public class InitializeCatsInteractorTest {
    private InMemoryCatDataAccessObject catRepository;
    private CatFactory catFactory;

    @BeforeEach
    void setUp() {
        catRepository = new InMemoryCatDataAccessObject();
        catFactory = new CatFactory();
    }

    @Test
    void successInitializeCatsUseCase() {

        final InitializeCatsInputData inputData = new InitializeCatsInputData("chiually");

        final Cat cat = catFactory.create("miso", inputData.getOwnerUsername());
        catRepository.saveCat(cat);

        final InitializeCatsOutputBoundary successPresenter = new InitializeCatsOutputBoundary() {
            @Override
            public void prepareSuccessView(InitializeCatsOutputData initializeCatsOutputData) {
                for (Cat cat : initializeCatsOutputData.getCats()) {
                    assertEquals(cat.getName(), "miso");
                }
            }
        };

        final InitializeCatsInputBoundary interactor = new InitializeCatsInteractor(catRepository, successPresenter);
        interactor.execute(inputData);

    }
}
