package use_case.adoption;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class AdoptionInteractorTest {

    @Test
    void successAdoptionTest() {
        final AdoptionInputData inputData = new AdoptionInputData("barfi", "manahill");
        final AdoptionOutputBoundary successPresenter = new AdoptionOutputBoundary() {
            @Override
            public void prepareSuccessView(AdoptionOutputData outputData) {
                assertEquals("manahill", outputData.getOwnerName());
                assertFalse(outputData.isUseCaseFailed());
                assertEquals("barfi", outputData.getCatName());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected");
            }
        };

        final AdoptionInputBoundary interactor = new AdoptionInteractor(successPresenter);
        interactor.execute(inputData);
    }

}
