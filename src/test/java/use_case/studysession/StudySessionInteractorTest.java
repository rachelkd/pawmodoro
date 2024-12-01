package use_case.studysession;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StudySessionInteractorTest {

    @Test
    void successSwitchToBreakSessionViewTest() {
        final TestStudySessionPresenter testPresenter = new TestStudySessionPresenter();
        final StudySessionInteractor interactor = new StudySessionInteractor(testPresenter);

        interactor.switchToBreakSessionView();

        assertTrue(testPresenter.wasSwitchToBreakSessionViewCalled(),
                "switchToBreakSessionView should have been called on the presenter");
    }

    @Test
    void successSwitchToSetupSessionViewTest() {
        final TestStudySessionPresenter testPresenter = new TestStudySessionPresenter();
        final StudySessionInteractor interactor = new StudySessionInteractor(testPresenter);

        interactor.switchToSetupSessionView();

        assertTrue(testPresenter.wasSwitchToSetupSessionViewCalled(),
                "switchToSetupSessionView should have been called on the presenter");
    }

    @Test
    void successLogoutTest() {
        final TestStudySessionPresenter testPresenter = new TestStudySessionPresenter();
        final StudySessionInteractor interactor = new StudySessionInteractor(testPresenter);

        interactor.logout();

        assertTrue(testPresenter.wasSwitchToLoginViewCalled(),
                "switchToLoginView should have been called on the presenter");
        assertTrue(testPresenter.wasPrepareLoginViewCalled(),
                "prepareLoginView should have been called on the presenter after logout");
    }

    @Test
    void successStopStudyTimerTest() {
        final TestStudySessionPresenter testPresenter = new TestStudySessionPresenter();
        final StudySessionInteractor interactor = new StudySessionInteractor(testPresenter);

        int interval = 25;
        boolean isRunning = false;
        StudySessionInputData inputData = new StudySessionInputData(interval, isRunning);

        interactor.stopStudyTimer(inputData);

        assertTrue(testPresenter.wasStopStudyTimerCalled(),
                "stopStudyTimer should have been called on the presenter");

        assertEquals(interval, testPresenter.getLastStudySessionOutputData().getCurrentWorkInterval(),
                "The interval passed to the presenter should match the input interval");

        assertFalse(testPresenter.getLastStudySessionOutputData().isTimerRunning(),
                "isStudySessionSuccess should be false because the timer was stopped manually");
    }

    @Test
    void successHandleTest() {
        final TestStudySessionPresenter testPresenter = new TestStudySessionPresenter();
        final StudySessionInteractor interactor = new StudySessionInteractor(testPresenter);

        int interval = 20; // 20 minutes
        boolean isRunning = true; // Timer successfully completed
        StudySessionInputData inputData = new StudySessionInputData(interval, isRunning);

        interactor.handle(inputData);

        assertTrue(testPresenter.wasStopStudyTimerCalled(),
                "stopStudyTimer should have been called through the handle method");

        assertEquals(interval, testPresenter.getLastStudySessionOutputData().getCurrentWorkInterval(),
                "The interval passed to the presenter should match the input interval");

        assertTrue(testPresenter.getLastStudySessionOutputData().isTimerRunning(),
                "isStudySessionSuccess should be true because the timer finished successfully");
    }

    @Test
    void switchToBreakSessionViewFailsIfNotCalledTest() {
        final TestStudySessionPresenter testPresenter = new TestStudySessionPresenter();

        assertFalse(testPresenter.wasSwitchToBreakSessionViewCalled(),
                "switchToBreakSessionView should not have been called initially");
    }

    @Test
    void switchToSetupSessionViewFailsIfNotCalledTest() {
        final TestStudySessionPresenter testPresenter = new TestStudySessionPresenter();

        assertFalse(testPresenter.wasSwitchToSetupSessionViewCalled(),
                "switchToSetupSessionView should not have been called initially");
    }

    @Test
    void switchToLoginViewFailsIfNotCalledTest() {
        final TestStudySessionPresenter testPresenter = new TestStudySessionPresenter();

        assertFalse(testPresenter.wasSwitchToLoginViewCalled(),
                "switchToLoginView should not have been called initially");
    }

    @Test
    void prepareLoginViewFailsIfNotCalledTest() {
        final TestStudySessionPresenter testPresenter = new TestStudySessionPresenter();

        assertFalse(testPresenter.wasPrepareLoginViewCalled(),
                "prepareLoginView should not have been called initially");
    }

    @Test
    void stopStudyTimerFailsIfNotCalledTest() {
        final TestStudySessionPresenter testPresenter = new TestStudySessionPresenter();

        assertFalse(testPresenter.wasStopStudyTimerCalled(),
                "stopStudyTimer should not have been called initially");
    }

    private static final class TestStudySessionPresenter implements StudySessionOutputBoundary {
        private boolean switchToBreakSessionViewCalled;
        private boolean switchToSetupSessionViewCalled;
        private boolean switchToLoginViewCalled;
        private boolean stopStudyTimerCalled;
        private boolean prepareLoginViewCalled;
        private StudySessionOutputData lastStudySessionOutputData;

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
        public void stopStudyTimer(StudySessionOutputData studySessionOutputData) {
            stopStudyTimerCalled = true;
            lastStudySessionOutputData = studySessionOutputData;
        }

        @Override
        public void prepareLoginView() {
            prepareLoginViewCalled = true;
        }

        public boolean wasSwitchToBreakSessionViewCalled() {
            return switchToBreakSessionViewCalled;
        }

        public boolean wasSwitchToSetupSessionViewCalled() {
            return switchToSetupSessionViewCalled;
        }

        public boolean wasSwitchToLoginViewCalled() {
            return switchToLoginViewCalled;
        }

        public boolean wasStopStudyTimerCalled() {
            return stopStudyTimerCalled;
        }

        public boolean wasPrepareLoginViewCalled() {
            return prepareLoginViewCalled;
        }

        public StudySessionOutputData getLastStudySessionOutputData() {
            return lastStudySessionOutputData;
        }
    }
}


