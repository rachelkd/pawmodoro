package use_case.get_cat_fact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import data_access.InMemoryCatFactDataAccessObject;

class GetCatFactInteractorTest {

    @Test
    void successTest() {
        final String expectedCatFact = "Cats sleep for around 70% of their lives.";
        final CatFactDataAccessInterface catFactRepository = new InMemoryCatFactDataAccessObject(
                expectedCatFact, false, null);

        final GetCatFactOutputBoundary successPresenter = new GetCatFactOutputBoundary() {
            @Override
            public void prepareSuccessView(GetCatFactOutputData catFactData) {
                assertEquals(expectedCatFact, catFactData.getCatFact());
                assertFalse(catFactData.isUseCaseFailed());
                assertNull(catFactData.getErrorMessage());
            }

            @Override
            public void prepareFailView(GetCatFactOutputData catFactData) {
                fail("Use case failure is unexpected.");
            }
        };

        final GetCatFactInputBoundary interactor = new GetCatFactInteractor(
                catFactRepository, successPresenter);

        interactor.execute();
    }

    @Test
    void failureTest() {
        final String expectedErrorMessage = "API is unavailable";
        final CatFactDataAccessInterface catFactRepository = new InMemoryCatFactDataAccessObject(
                null, true, expectedErrorMessage);

        final GetCatFactOutputBoundary failurePresenter = new GetCatFactOutputBoundary() {
            @Override
            public void prepareSuccessView(GetCatFactOutputData catFactData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(GetCatFactOutputData catFactData) {
                assertNull(catFactData.getCatFact());
                assertTrue(catFactData.isUseCaseFailed());
                assertEquals(expectedErrorMessage, catFactData.getErrorMessage());
            }
        };

        final GetCatFactInputBoundary interactor = new GetCatFactInteractor(
                catFactRepository, failurePresenter);

        interactor.execute();
    }
}
