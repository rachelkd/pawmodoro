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
    private String catName = "";
    private boolean isSuccess = true;

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

    public boolean getIsSuccess() {
        return isSuccess;
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

    /**
     * Sets isSuccess to false when the timer is stopped.
     */
    public void setTimerStopped() {
        this.isSuccess = false;
    }

    public void setIsSuccess(boolean success) {
        this.isSuccess = success;
    }

    /**
     * Resets the work interval to the default value of 25 minutes.
     */
    public void resetToDefaultWorkInterval() {
        this.workInterval = Constants.DEFAULT_WORK_DURATION_MS;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
