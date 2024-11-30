package use_case.breaksession;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BreakSessionInteractorTest {

    private static class TestBreakSessionPresenter implements BreakSessionOutputBoundary {
        boolean switchToStudySessionViewCalled = false;
        boolean switchToLoginViewCalled = false;
        boolean prepareLoginViewCalled = false;

        @Override
        public void switchToStudySessionView() {
            switchToStudySessionViewCalled = true;
        }

        @Override
        public void switchToLoginView() {
            switchToLoginViewCalled = true;
        }

        @Override
        public void prepareLoginView() {
            prepareLoginViewCalled = true;
        }
    }

    @Test
    void successSwitchToStudySessionViewTest() {
        TestBreakSessionPresenter testPresenter = new TestBreakSessionPresenter();
        BreakSessionInteractor interactor = new BreakSessionInteractor(testPresenter);

        interactor.switchToStudySessionView();

        assertTrue(testPresenter.switchToStudySessionViewCalled,
                "switchToStudySessionView should have been called on the presenter");
    }

    @Test
    void successLogoutTest() {
        TestBreakSessionPresenter testPresenter = new TestBreakSessionPresenter();
        BreakSessionInteractor interactor = new BreakSessionInteractor(testPresenter);

        interactor.logout();

        assertTrue(testPresenter.switchToLoginViewCalled,
                "switchToLoginView should have been called on the presenter");
        assertTrue(testPresenter.prepareLoginViewCalled,
                "prepareLoginView should have been called on the presenter after logout");
    }

    @Test
    void switchToStudySessionViewFailsIfNotCalledTest() {
        TestBreakSessionPresenter testPresenter = new TestBreakSessionPresenter();

        assertFalse(testPresenter.switchToStudySessionViewCalled,
                "switchToStudySessionView should not have been called initially");
    }

    @Test
    void switchToLoginViewFailsIfNotCalledTest() {
        TestBreakSessionPresenter testPresenter = new TestBreakSessionPresenter();

        assertFalse(testPresenter.switchToLoginViewCalled,
                "switchToLoginView should not have been called initially");
    }

    @Test
    void prepareLoginViewFailsIfNotCalledTest() {
        TestBreakSessionPresenter testPresenter = new TestBreakSessionPresenter();

        assertFalse(testPresenter.prepareLoginViewCalled,
                "prepareLoginView should not have been called initially");
    }
}


