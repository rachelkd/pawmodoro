package use_case.logout;

/**
 * The Logout Interactor handles the business logic for logging out users.
 * It validates the current session and coordinates with the data access layer
 * to clear authentication state.
 */
public class LogoutInteractor implements LogoutInputBoundary {
    private final LogoutUserDataAccessInterface userDataAccessObject;
    private final LogoutOutputBoundary logoutPresenter;

    public LogoutInteractor(LogoutUserDataAccessInterface userDataAccessInterface,
            LogoutOutputBoundary logoutOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.logoutPresenter = logoutOutputBoundary;
    }

    @Override
    public void execute(LogoutInputData logoutInputData) {
        final String username = logoutInputData.getUsername();

        if (userDataAccessObject.getCurrentUsername() == null) {
            logoutPresenter.prepareFailView("No user is currently logged in.");
        }
        else if (!username.equals(userDataAccessObject.getCurrentUsername())) {
            logoutPresenter.prepareFailView("Cannot logout a different user than the one currently logged in.");
        }
        else {
            final boolean logoutSuccess = userDataAccessObject.logout();
            if (logoutSuccess) {
                userDataAccessObject.setCurrentUsername(null);
                logoutPresenter.prepareSuccessView(new LogoutOutputData(username, true));
            }
            else {
                logoutPresenter.prepareFailView("Failed to logout from authentication service.");
            }
        }
    }
}
