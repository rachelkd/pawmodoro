package use_case.change_password;

import entity.User;
import entity.UserFactory;
import entity.exceptions.DatabaseAccessException;

/**
 * The Change Password Interactor.
 */
public class ChangePasswordInteractor implements ChangePasswordInputBoundary {
    private final ChangePasswordUserDataAccessInterface userDataAccessObject;
    private final ChangePasswordOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public ChangePasswordInteractor(ChangePasswordUserDataAccessInterface changePasswordDataAccessInterface,
            ChangePasswordOutputBoundary changePasswordOutputBoundary,
            UserFactory userFactory) {
        this.userDataAccessObject = changePasswordDataAccessInterface;
        this.userPresenter = changePasswordOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(ChangePasswordInputData changePasswordInputData) {
        try {
            if (!userDataAccessObject.existsByName(changePasswordInputData.getUsername())) {
                userPresenter.prepareFailView("User does not exist.");
            }
            else if (changePasswordInputData.getPassword().isEmpty()) {
                userPresenter.prepareFailView("Password cannot be empty.");
            }
            else {
                final User user = userFactory.create(
                        changePasswordInputData.getUsername(),
                        "");
                userDataAccessObject.changePassword(user, changePasswordInputData.getPassword());

                final ChangePasswordOutputData changePasswordOutputData = new ChangePasswordOutputData(user.getName(),
                        false);
                userPresenter.prepareSuccessView(changePasswordOutputData);
            }
        }
        catch (DatabaseAccessException exception) {
            userPresenter.prepareFailView(
                    "Network error: Unable to change password. Please check your internet connection and try again.");
        }
    }
}
