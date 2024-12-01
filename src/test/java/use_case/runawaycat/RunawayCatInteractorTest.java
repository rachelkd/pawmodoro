package use_case.runawaycat;

import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.Test;

import data_access.InMemoryCatDataAccessObject;

class RunawayCatInteractorTest {
    @Test
    void successRunawayCatTest() {
        final RunawayCatInputData inputData = new RunawayCatInputData("sven", "miley");
        final InMemoryCatDataAccessObject catRepository = new InMemoryCatDataAccessObject();

        final RunawayCatOutputBoundary successPresenter = new RunawayCatOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                assertFalse(catRepository.existsByNameAndOwner(inputData.getCatName(), inputData.getOwnerName()));

            }
        };

        final RunawayCatInputBoundary interactor = new RunawayCatInteractor(successPresenter, catRepository);
        interactor.execute(inputData);
    }
}
