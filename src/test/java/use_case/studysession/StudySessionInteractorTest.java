package use_case.studysession;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class StudySessionInteractorTest {

    @Test
    void successSwitchToBreakSessionViewTest() {
        final TestStudySessionPresenter testPresenter = new TestStudySessionPresenter();
        final StudySessionInteractor interactor = new StudySessionInteractor(testPresenter);

        interactor.switchToBreakSessionView();

        assertTrue(testPresenter.getSwitchToBreakSessionViewCalled(),
                "switchToBreakSessionView should have been called on the presenter");
    }

    @Test
    void successSwitchToSetupSessionViewTest() {
        final TestStudySessionPresenter testPresenter = new TestStudySessionPresenter();
        final StudySessionInteractor interactor = new StudySessionInteractor(testPresenter);

        interactor.switchToSetupSessionView();

        assertTrue(testPresenter.getSwitchToSetupSessionViewCalled(),
                "switchToSetupSessionView should have been called on the presenter");
    }

    @Test
    void successLogoutTest() {
        final TestStudySessionPresenter testPresenter = new TestStudySessionPresenter();
        final StudySessionInteractor interactor = new StudySessionInteractor(testPresenter);

        interactor.logout();

        assertTrue(testPresenter.getSwitchToLoginViewCalled(),
                "switchToLoginView should have been called on the presenter");
        assertTrue(testPresenter.getPrepareLoginViewCalled(),
                "prepareLoginView should have been called on the presenter after logout");
    }

    @Test
    void successStopStudyTimerTest() {
        final TestStudySessionPresenter testPresenter = new TestStudySessionPresenter();
        final StudySessionInteractor interactor = new StudySessionInteractor(testPresenter);

        interactor.stopStudyTimer();

        assertTrue(testPresenter.getStopStudyTimerCalled(),
                "stopStudyTimer should have been called on the presenter");
    }

    @Test
    void switchToBreakSessionViewFailsIfNotCalledTest() {
        final TestStudySessionPresenter testPresenter = new TestStudySessionPresenter();

        assertFalse(testPresenter.getSwitchToBreakSessionViewCalled(),
                "switchToBreakSessionView should not have been called initially");
    }

    @Test
    void switchToSetupSessionViewFailsIfNotCalledTest() {
        final TestStudySessionPresenter testPresenter = new TestStudySessionPresenter();

        assertFalse(testPresenter.getSwitchToSetupSessionViewCalled(),
                "switchToSetupSessionView should not have been called initially");
    }

    @Test
    void switchToLoginViewFailsIfNotCalledTest() {
        final TestStudySessionPresenter testPresenter = new TestStudySessionPresenter();

        assertFalse(testPresenter.getSwitchToLoginViewCalled(),
                "switchToLoginView should not have been called initially");
    }

    @Test
    void prepareLoginViewFailsIfNotCalledTest() {
        final TestStudySessionPresenter testPresenter = new TestStudySessionPresenter();

        assertFalse(testPresenter.getPrepareLoginViewCalled(),
                "prepareLoginView should not have been called initially");
    }

    @Test
    void stopStudyTimerFailsIfNotCalledTest() {
        final TestStudySessionPresenter testPresenter = new TestStudySessionPresenter();

        assertFalse(testPresenter.getStopStudyTimerCalled(),
                "stopStudyTimer should not have been called initially");
    }

    private static final class TestStudySessionPresenter implements StudySessionOutputBoundary {
        private boolean switchToBreakSessionViewCalled;
        private boolean switchToSetupSessionViewCalled;
        private boolean switchToLoginViewCalled;
        private boolean stopStudyTimerCalled;
        private boolean prepareLoginViewCalled;

        @Override
        public void switchToBreakSessionView() {
            switchToBreakSessionViewCalled = true;
        }

        @Override
        public void switchToSetupSessionView() {
            switchToSetupSessionViewCalled = true;
        }

        @Override
        public void switchToLoginView() {
            switchToLoginViewCalled = true;
        }

        @Override
        public void stopStudyTimer() {
            stopStudyTimerCalled = true;
        }

        @Override
        public void prepareLoginView() {
            prepareLoginViewCalled = true;
        }

        public boolean getSwitchToBreakSessionViewCalled() {
            return switchToBreakSessionViewCalled;
        }

        public boolean getSwitchToSetupSessionViewCalled() {
            return switchToSetupSessionViewCalled;
        }

        public boolean getSwitchToLoginViewCalled() {
            return switchToLoginViewCalled;
        }

        public boolean getStopStudyTimerCalled() {
            return stopStudyTimerCalled;
        }

        public boolean getPrepareLoginViewCalled() {
            return prepareLoginViewCalled;
        }
    }
}
