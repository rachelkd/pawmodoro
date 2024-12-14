package use_case.signup;

import entity.User;
import entity.UserFactory;
import entity.exceptions.DatabaseAccessException;

/**
 * The Signup Interactor.
 */
public class SignupInteractor implements SignupInputBoundary {
    private final SignupUserDataAccessInterface userDataAccessObject;
    private final SignupOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface userDataAccessObject,
            SignupOutputBoundary signupOutputBoundary,
            UserFactory userFactory) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        try {
            if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
                userPresenter.prepareFailView("User already exists.");
            }
            else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
                userPresenter.prepareFailView("Passwords don't match.");
            }
            else if (signupInputData.getUsername().isEmpty() || signupInputData.getPassword().isEmpty()) {
                userPresenter.prepareFailView("Please fill out all fields.");
            }
            else {
                final User user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword());
                userDataAccessObject.save(user);

                final SignupOutputData signupOutputData = new SignupOutputData(user.getName(), false);
                userPresenter.prepareSuccessView(signupOutputData);
            }
        }
        catch (DatabaseAccessException exception) {
            userPresenter.prepareFailView(
                    "Network error: Unable to sign up. Please check your internet connection and try again.");
        }
    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }
}
