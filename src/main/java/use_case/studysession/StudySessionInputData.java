package use_case.studysession;

/**
 * Input Data for Study Session.
 */
public class StudySessionInputData {
    private final int currentWorkInterval;
    private final boolean isTimerRunning;

    public StudySessionInputData(int currentWorkInterval, boolean isTimerRunning) {
        this.currentWorkInterval = currentWorkInterval;
        this.isTimerRunning = isTimerRunning;
    }

    public int getCurrentWorkInterval() {
        return currentWorkInterval;
    }

    public boolean isTimerRunning() {
        return isTimerRunning;
    }
}
