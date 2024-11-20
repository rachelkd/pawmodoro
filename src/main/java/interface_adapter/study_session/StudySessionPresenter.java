package interface_adapter.study_session;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.login.LoginState;
import use_case.studysession.StudySessionOutputBoundary;

/**
 * Presenter for the study session use case.
 * Handles the presentation logic for study session related operations.
 */
public class StudySessionPresenter implements StudySessionOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final StudySessionViewModel studySessionViewModel;
    private final LoginViewModel loginViewModel;

    public StudySessionPresenter(ViewManagerModel viewManagerModel,
            StudySessionViewModel studySessionViewModel,
            LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.studySessionViewModel = studySessionViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void switchToSetupSessionView() {
        viewManagerModel.setState("setup session");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToLoginView() {
        prepareLoginView();
        viewManagerModel.setState("log in");
        viewManagerModel.firePropertyChanged();
    }

    public void prepareLoginView() {
        LoginState currentState = loginViewModel.getState();
        // Clear logged-in user's password but not username
        currentState.setPassword("");
        currentState.setLoginError("");
        loginViewModel.setState(currentState);
        loginViewModel.firePropertyChanged();
    }
}
