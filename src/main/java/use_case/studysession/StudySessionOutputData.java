package use_case.studysession;

/**
 * Output Data for Study Session Use Case.
 */
public class StudySessionOutputData {
    private final int currentWorkInterval;
    private final boolean isTimerRunning;

    public StudySessionOutputData(int currentWorkInterval, boolean isTimerRunning) {
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
