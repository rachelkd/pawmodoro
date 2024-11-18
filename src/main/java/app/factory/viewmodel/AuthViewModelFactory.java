package app.factory.viewmodel;

import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.change_password.LoggedInViewModel;

/**
 * Factory for creating authentication-related view models.
 */
public class AuthViewModelFactory {
    public LoginViewModel createLoginViewModel() {
        return new LoginViewModel();
    }

    public SignupViewModel createSignupViewModel() {
        return new SignupViewModel();
    }

    public LoggedInViewModel createLoggedInViewModel() {
        return new LoggedInViewModel();
    }
}
