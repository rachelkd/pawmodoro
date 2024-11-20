package app.builder.view.auth;

import view.LoggedInView;
import view.LoginView;
import view.SignupView;

/**
 * Container for auth-related views.
 */
public class AuthViews {
    private final LoginView loginView;
    private final SignupView signupView;
    private final LoggedInView loggedInView;

    public AuthViews(LoginView loginView,
            SignupView signupView,
            LoggedInView loggedInView) {
        this.loginView = loginView;
        this.signupView = signupView;
        this.loggedInView = loggedInView;
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public SignupView getSignupView() {
        return signupView;
    }

    public LoggedInView getLoggedInView() {
        return loggedInView;
    }
}
