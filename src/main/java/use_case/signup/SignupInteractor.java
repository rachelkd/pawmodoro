package use_case.signup;

import entity.User;
import entity.UserFactory;
import entity.exceptions.DatabaseAccessException;

/**
 * The Signup Interactor.
 */
public class SignupInteractor implements SignupInputBoundary {
    private static final int MIN_PASSWORD_LENGTH = 6;
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
            else if (signupInputData.getUsername().isEmpty() || signupInputData.getPassword().isEmpty()
                    || signupInputData.getEmail().isEmpty()) {
                userPresenter.prepareFailView("Please fill out all fields.");
            }
            else if (!isValidEmail(signupInputData.getEmail())) {
                userPresenter.prepareFailView("Please enter a valid email address.");
            }
            else {
                final User user = userFactory.create(
                        signupInputData.getUsername(),
                        signupInputData.getEmail());
                userDataAccessObject.save(user, signupInputData.getPassword());

                final SignupOutputData signupOutputData = new SignupOutputData(user.getName(), false);
                userPresenter.prepareSuccessView(signupOutputData);
            }
        }
        catch (DatabaseAccessException exception) {
            userPresenter.prepareFailView(exception.getMessage());
        }
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }
}
