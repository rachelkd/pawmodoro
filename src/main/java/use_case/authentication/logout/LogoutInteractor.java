package use_case.authentication.logout;

/**
 * The Logout Interactor.
 */
public class LogoutInteractor implements LogoutInputBoundary {
    private LogoutUserDataAccessInterface userDataAccessObject;
    private LogoutOutputBoundary logoutPresenter;

    public LogoutInteractor(LogoutUserDataAccessInterface userDataAccessInterface,
            LogoutOutputBoundary logoutOutputBoundary) {
        // Save the DAO and Presenter in the instance variables.
        this.userDataAccessObject = userDataAccessInterface;
        this.logoutPresenter = logoutOutputBoundary;
    }

    @Override
    public void execute(LogoutInputData logoutInputData) {
        // Implement the logic of the Logout Use Case
        // * get the username out of the input data,
        // * set the username to null in the DAO
        // * instantiate the `LogoutOutputData`, which needs to contain the username.
        // * tell the presenter to prepare a success view.
        final String username = logoutInputData.getUsername();
        userDataAccessObject.setCurrentUsername("");
        final LogoutOutputData logoutOutputData = new LogoutOutputData(username, false);
        logoutPresenter.prepareSuccessView(logoutOutputData);
    }
}
