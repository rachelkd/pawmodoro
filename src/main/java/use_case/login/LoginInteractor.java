package use_case.login;

import entity.User;
import entity.exceptions.DatabaseAccessException;

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
        try {
            if (!userDataAccessObject.existsByName(username)) {
                loginPresenter.prepareFailView(username + ": Account does not exist.");
            }
            else {
                final String pwd = userDataAccessObject.get(username).getPassword();
                if (!password.equals(pwd)) {
                    loginPresenter.prepareFailView("Incorrect password for \"" + username + "\".");
                }
                else {
                    final User user = userDataAccessObject.get(loginInputData.getUsername());
                    userDataAccessObject.setCurrentUsername(user.getName());
                    final LoginOutputData loginOutputData = new LoginOutputData(user.getName(), false);
                    loginPresenter.prepareSuccessView(loginOutputData);
                }
            }
        }
        catch (DatabaseAccessException exception) {
            loginPresenter.prepareFailView(
                    "Network error: Unable to login. Please check your internet connection and try again.");
        }
    }

    @Override
    public void switchToSignUpView() {
        loginPresenter.switchToSignUpView();
    }
}
