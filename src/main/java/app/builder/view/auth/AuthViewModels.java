package app.builder.view.auth;

import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;

/**
 * Container for auth-related view models.
 */
public class AuthViewModels {
    private final LoginViewModel loginViewModel;
    private final SignupViewModel signupViewModel;
    private final LoggedInViewModel loggedInViewModel;

    public AuthViewModels(LoginViewModel loginViewModel,
            SignupViewModel signupViewModel,
            LoggedInViewModel loggedInViewModel) {
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }

    public SignupViewModel getSignupViewModel() {
        return signupViewModel;
    }

    public LoggedInViewModel getLoggedInViewModel() {
        return loggedInViewModel;
    }
}
