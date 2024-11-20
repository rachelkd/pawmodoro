package use_case.setupsession;


/**
 * The Input Data for the setting up session Use Case.
 */
public class SetupSessionInputData {
    private final int studyTime;
    private final int breakTime;

    public SetupSessionInputData(int studyTime, int breakTime) {
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
