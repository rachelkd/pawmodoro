package interface_adapter.break_session;

import constants.Constants;

/**
 * Study session state.
 */
public class BreakSessionState {
    private String username = "";
    private String loginError;
    private String password = "";
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLoginError(String usernameError) {
        this.loginError = usernameError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the break interval duration in milliseconds.
     *
     * @return The break interval duration in milliseconds.
     */
    public long getBreakInterval() {
        return breakInterval;
    }

    /**
     * Sets the break interval duration in milliseconds.
     *
     * @param breakInterval The new break interval duration in milliseconds.
     */
    public void setBreakInterval(long breakInterval) {
        this.breakInterval = breakInterval;
    }

    /**
     * Resets the break interval to the default value of 5 minutes.
     */
    public void resetToDefaultBreakInterval() {
        this.breakInterval = Constants.DEFAULT_BREAK_DURATION_MS;
    }
}

