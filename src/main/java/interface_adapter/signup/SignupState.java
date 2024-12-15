package interface_adapter.signup;

/**
 * The state for the Signup View Model.
 */
public class SignupState {
    private String username = "";
    private String email = "";
    private String password = "";
    private String repeatPassword = "";
    private String usernameError;
    private String emailError;
    private String passwordError;
    private boolean useCaseFailed = false;

    public SignupState(SignupState copy) {
        username = copy.username;
        email = copy.email;
        password = copy.password;
        repeatPassword = copy.repeatPassword;
        usernameError = copy.usernameError;
        emailError = copy.emailError;
        passwordError = copy.passwordError;
        useCaseFailed = copy.useCaseFailed;
    }

    public SignupState() {
        // Empty constructor for SignupState
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public String getEmailError() {
        return emailError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public void setUsernameError(String error) {
        this.usernameError = error;
    }

    public void setEmailError(String error) {
        this.emailError = error;
    }

    public void setPasswordError(String error) {
        this.passwordError = error;
    }

    public void setUseCaseFailed(boolean failed) {
        this.useCaseFailed = failed;
    }

    @Override
    public String toString() {
        return "SignupState{"
                + "username='" + username + '\''
                + ", email='" + email + '\''
                + ", password='" + password + '\''
                + ", repeatPassword='" + repeatPassword + '\''
                + ", useCaseFailed=" + useCaseFailed
                + '}';
    }
}
