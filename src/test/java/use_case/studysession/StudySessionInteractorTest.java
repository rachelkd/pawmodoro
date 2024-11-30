package use_case.studysession;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudySessionInteractorTest {

    private static class TestStudySessionPresenter implements StudySessionOutputBoundary {
        boolean switchToSetupSessionViewCalled = false;
        boolean switchToLoginViewCalled = false;
        boolean switchToBreakSessionViewCalled = false;
        boolean stopStudyTimerCalled = false;
        boolean prepareLoginViewCalled = false;

        @Override
        public void switchToSetupSessionView() {
            switchToSetupSessionViewCalled = true;
        }

        @Override
        public void switchToLoginView() {
            switchToLoginViewCalled = true;
        }

        @Override
        public void switchToBreakSessionView() {
            switchToBreakSessionViewCalled = true;
        }

        @Override
        public void stopStudyTimer() {
            stopStudyTimerCalled = true;
        }

        @Override
        public void prepareLoginView() {
            prepareLoginViewCalled = true;
        }
    }

    @Test
    void successSwitchToSetupSessionViewTest() {
        TestStudySessionPresenter testPresenter = new TestStudySessionPresenter();
        StudySessionInteractor interactor = new StudySessionInteractor(testPresenter);

        interactor.switchToSetupSessionView();

        assertTrue(testPresenter.switchToSetupSessionViewCalled,
                "switchToSetupSessionView should have been called on the presenter");
    }

    @Test
    void successLogoutTest() {
        TestStudySessionPresenter testPresenter = new TestStudySessionPresenter();
        StudySessionInteractor interactor = new StudySessionInteractor(testPresenter);
        interactor.logout();
        assertTrue(testPresenter.switchToLoginViewCalled,
                "switchToLoginView should have been called on the presenter");
        assertTrue(testPresenter.prepareLoginViewCalled,
                "prepareLoginView should have been called on the presenter after logout");
    }

    @Test
    void successSwitchToBreakSessionViewTest() {
        TestStudySessionPresenter testPresenter = new TestStudySessionPresenter();
        StudySessionInteractor interactor = new StudySessionInteractor(testPresenter);
        interactor.switchToBreakSessionView();
        assertTrue(testPresenter.switchToBreakSessionViewCalled,
                "switchToBreakSessionView should have been called on the presenter");
    }

    @Test
    void successStopStudyTimerTest() {
        TestStudySessionPresenter testPresenter = new TestStudySessionPresenter();
        StudySessionInteractor interactor = new StudySessionInteractor(testPresenter);
        interactor.stopStudyTimer();
        assertTrue(testPresenter.stopStudyTimerCalled,
                "stopStudyTimer should have been called on the presenter");
    }

    @Test
    void switchToSetupSessionViewFailsIfNotCalledTest() {
        TestStudySessionPresenter testPresenter = new TestStudySessionPresenter();
        assertFalse(testPresenter.switchToSetupSessionViewCalled,
                "switchToSetupSessionView should not have been called initially");
    }

    @Test
    void switchToLoginViewFailsIfNotCalledTest() {
        TestStudySessionPresenter testPresenter = new TestStudySessionPresenter();
        assertFalse(testPresenter.switchToLoginViewCalled,
                "switchToLoginView should not have been called initially");
    }

    @Test
    void switchToBreakSessionViewFailsIfNotCalledTest() {
        TestStudySessionPresenter testPresenter = new TestStudySessionPresenter();
        assertFalse(testPresenter.switchToBreakSessionViewCalled,
                "switchToBreakSessionView should not have been called initially");
    }

    @Test
    void stopStudyTimerFailsIfNotCalledTest() {
        TestStudySessionPresenter testPresenter = new TestStudySessionPresenter();
        assertFalse(testPresenter.stopStudyTimerCalled,
                "stopStudyTimer should not have been called initially");
    }
}


