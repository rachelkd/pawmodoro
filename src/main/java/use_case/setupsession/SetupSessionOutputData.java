package use_case.setupsession;

/**
 * Output data for the setup session use case.
 */

public class SetupSessionOutputData {
    private final int studyTime;
    private final int breakTime;

    public SetupSessionOutputData(int studyTime, int breakTime) {
        this.studyTime = studyTime;
        this.breakTime = breakTime;
    }

    public int getStudyTime() {
        return studyTime;
    }

    public int getBreakTime() {
        return breakTime;
    }
}
