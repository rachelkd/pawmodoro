package use_case.login;

import entity.User;
import entity.exceptions.DatabaseAccessException;
import entity.exceptions.UserNotFoundException;

/**
 * The Login Interactor.
 */
public class LoginInteractor implements LoginInputBoundary {
    private final LoginUserDataAccessInterface userDataAccessObject;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
            LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        final String username = loginInputData.getUsername();
        final String password = loginInputData.getPassword();

        if (username.isEmpty() || password.isEmpty()) {
            loginPresenter.prepareFailView("Username and password cannot be empty.");
        }
        else {
            try {
                // First check if the user exists
                if (!userDataAccessObject.existsByName(username)) {
                    loginPresenter.prepareFailView("User does not exist.");
                }
                else {
                    // Get the user by username to get their email
                    final User user = userDataAccessObject.get(username);

                    try {
                        // Attempt to authenticate with Supabase using email and password
                        final User authenticatedUser = userDataAccessObject.authenticate(user.getEmail(), password);

                        // Save the authenticated user's session
                        userDataAccessObject.setCurrentUsername(authenticatedUser.getName());
                        final LoginOutputData loginOutputData = new LoginOutputData(authenticatedUser.getName(), false);
                        loginPresenter.prepareSuccessView(loginOutputData);
                    }
                    catch (DatabaseAccessException authException) {
                        // Handle authentication failure (wrong password)
                        loginPresenter.prepareFailView("Incorrect password.");
                    }
                }
            }
            catch (DatabaseAccessException | UserNotFoundException exception) {
                loginPresenter.prepareFailView(exception.getMessage());
            }
        }
    }

    @Override
    public void switchToSignUpView() {
        loginPresenter.switchToSignUpView();
    }
}
