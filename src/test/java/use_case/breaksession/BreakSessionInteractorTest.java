package use_case.breaksession;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class BreakSessionInteractorTest {

    @Test
    void successSwitchToStudySessionViewTest() {
        final TestBreakSessionPresenter testPresenter = new TestBreakSessionPresenter();
        final BreakSessionInteractor interactor = new BreakSessionInteractor(testPresenter);

        interactor.switchToStudySessionView();

        assertTrue(testPresenter.switchToStudySessionViewCalled,
                "switchToStudySessionView should have been called on the presenter");
    }

    @Test
    void successLogoutTest() {
        final TestBreakSessionPresenter testPresenter = new TestBreakSessionPresenter();
        final BreakSessionInteractor interactor = new BreakSessionInteractor(testPresenter);

        interactor.logout();

        assertTrue(testPresenter.switchToLoginViewCalled,
                "switchToLoginView should have been called on the presenter");
        assertTrue(testPresenter.prepareLoginViewCalled,
                "prepareLoginView should have been called on the presenter after logout");
    }

    @Test
    void switchToStudySessionViewFailsIfNotCalledTest() {
        final TestBreakSessionPresenter testPresenter = new TestBreakSessionPresenter();

        assertFalse(testPresenter.switchToStudySessionViewCalled,
                "switchToStudySessionView should not have been called initially");
    }

    @Test
    void switchToLoginViewFailsIfNotCalledTest() {
        final TestBreakSessionPresenter testPresenter = new TestBreakSessionPresenter();

        assertFalse(testPresenter.switchToLoginViewCalled,
                "switchToLoginView should not have been called initially");
    }

    @Test
    void prepareLoginViewFailsIfNotCalledTest() {
        final TestBreakSessionPresenter testPresenter = new TestBreakSessionPresenter();

        assertFalse(testPresenter.prepareLoginViewCalled,
                "prepareLoginView should not have been called initially");
    }

    private static final class TestBreakSessionPresenter implements BreakSessionOutputBoundary {
        private boolean switchToStudySessionViewCalled;
        private boolean switchToLoginViewCalled;
        private boolean prepareLoginViewCalled;

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
}
