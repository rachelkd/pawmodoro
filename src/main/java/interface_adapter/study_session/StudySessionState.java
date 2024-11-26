package interface_adapter.study_session;

import constants.Constants;

/**
 * Study session state.
 */
public class StudySessionState {
    private String username = "";
    private String loginError;
    private String password = "";
    private long workInterval = Constants.DEFAULT_WORK_DURATION_MS;
    private long breakInterval = Constants.DEFAULT_BREAK_DURATION_MS;

    public String getUsername() {
        return username;
    }

    public String getLoginError() {
        return loginError;
    }

    public String getPassword() {
        return password;
    }

    public long getWorkInterval() {
        return workInterval;
    }

    public long getBreakInterval() {
        return breakInterval;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLoginError(String usernameError) {
        this.loginError = usernameError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setWorkInterval(long workInterval) {
        this.workInterval = workInterval;
    }

    public void setBreakInterval(long breakInterval) {
        this.breakInterval = breakInterval;
    }
    /**
     * Resets the work interval to the default value of 25 minutes.
     */
    public void resetToDefaultWorkInterval() {
        this.workInterval = Constants.DEFAULT_WORK_DURATION_MS;
    }
}
