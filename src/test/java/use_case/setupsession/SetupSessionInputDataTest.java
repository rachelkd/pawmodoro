package use_case.setupsession;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SetupSessionInputDataTest {
    @Test
    void successTest() {
        final SetupSessionInputData inputData = new SetupSessionInputData(35, 10);
        final SetupSessionOutputBoundary successPresenter = new SetupSessionOutputBoundary() {
            @Override
            public void prepareSuccessView(SetupSessionOutputData outputData) {
                assertEquals(35, outputData.getStudyTime());
                assertEquals(10, outputData.getBreakTime());
            }

            @Override
            public void switchToStudyView() {
                //This is expected
            }
        };
        final SetupSessionInputBoundary interactor = new SetupSessionInteractor(successPresenter);
        interactor.execute(inputData);
    }
    // There is no fail test for this particular usecase.
}
