package interface_adapter.setupsession;

/**
 * The state for setting up study session use case.
 */
public class SetupSessionState {
    private String studyTime = "1 min";
    private String breakTime = "1 min";

    public int getStudyTime() {
        return Integer.parseInt(studyTime.split(" ")[0]);
    }

    public int getBreakTime() {
        return Integer.parseInt(breakTime.split(" ")[0]);
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
