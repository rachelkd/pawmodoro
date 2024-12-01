package app.factory;

import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import view.LoggedInView;
import view.LoginView;
import view.SignupView;

/**
 * Factory for creating login-related views.
 */
public class LoginViewFactory {
    /**
     * Creates a LoginView.
     * @param loginViewModel the login view model
     * @return LoginView
     */
    public LoginView create(LoginViewModel loginViewModel) {
        return new LoginView(loginViewModel);
    }

    /**
     * Creates a Login View.
     * @param loginViewModel the login view model
     * @return LoginView
     */
    public LoginView createLoginView(LoginViewModel loginViewModel) {
        return new LoginView(loginViewModel);
    }

    /**
     * Creates a Signup View.
     * @param signupViewModel the signup view model
     * @return SignupView
     */
    public SignupView createSignupView(SignupViewModel signupViewModel) {
        return new SignupView(signupViewModel);
    }

    /**
     * Creates a LoggedInView.
     * @param loggedInViewModel the logged in view model
     * @return LoggedInView
     */
    public LoggedInView createLoggedInView(LoggedInViewModel loggedInViewModel) {
        return new LoggedInView(loggedInViewModel);
    }
}
