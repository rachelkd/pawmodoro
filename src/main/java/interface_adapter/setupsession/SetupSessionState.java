package interface_adapter.setupsession;

/**
 * The state for setting up study session use case.
 */
public class SetupSessionState {
    private String studyTime = "";
    private String breakTime = "";

    public int getStudyTime() {
        return Integer.valueOf(studyTime);
    }

    public int getBreakTime() {
        return Integer.valueOf(breakTime);
    }

    public void setStudyTime(String studyTime) {
        this.studyTime = studyTime;
    }

    public void setBreakTime(String breakTime) {
        this.breakTime = breakTime;
    }

    @Override
    public String toString() {
        return "SetupSessionState {studyTime =" + studyTime + '\'' + ", breakTime =" + '\'' + breakTime + "}";
    }

}
