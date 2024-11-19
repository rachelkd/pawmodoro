package app.factory.viewmodel;

import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;

/**
 * Factory for creating authentication-related view models.
 */
public class AuthViewModelFactory {
    /**
     * Creates a LoginViewModel.
     * @return a LoginViewModel
     */
    public LoginViewModel createLoginViewModel() {
        return new LoginViewModel();
    }

    /**
     * Creates a SignupViewModel.
     * @return a SignupViewModel
     */
    public SignupViewModel createSignupViewModel() {
        return new SignupViewModel();
    }

    /**
     * Creates a LoggedInViewModel.
     * @return a LoggedInViewModel
     */
    public LoggedInViewModel createLoggedInViewModel() {
        return new LoggedInViewModel();
    }
}
