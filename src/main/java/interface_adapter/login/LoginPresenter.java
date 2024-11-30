package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.runawaycat.RunawayCatState;
import interface_adapter.runawaycat.RunawayCatViewModel;
import interface_adapter.study_session.StudySessionState;
import interface_adapter.study_session.StudySessionViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final StudySessionViewModel studySessionViewModel;
    private final ViewManagerModel viewManagerModel;
    private final RunawayCatViewModel runawayCatViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
            StudySessionViewModel studySessionViewModel,
            LoginViewModel loginViewModel, RunawayCatViewModel runawayCatViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.studySessionViewModel = studySessionViewModel;
        this.loginViewModel = loginViewModel;
        this.runawayCatViewModel = runawayCatViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.

        final StudySessionState studySessionState = studySessionViewModel.getState();
        studySessionState.setUsername(response.getUsername());
        this.studySessionViewModel.setState(studySessionState);
        this.studySessionViewModel.firePropertyChanged();

        final RunawayCatState runawayCatState = runawayCatViewModel.getState();
        runawayCatState.setOwnerName(response.getUsername());
        this.runawayCatViewModel.setState(runawayCatState);

        switchToStudySessionView();
    }

    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }

    @Override
    public void switchToSignUpView() {
        this.viewManagerModel.setState("sign up");
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToStudySessionView() {
        this.viewManagerModel.setState("study session");
        this.viewManagerModel.firePropertyChanged();
    }
}
