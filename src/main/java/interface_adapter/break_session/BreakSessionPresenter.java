package interface_adapter.break_session;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.study_session.StudySessionViewModel;
import use_case.breaksession.BreakSessionOutputBoundary;

/**
 * Presenter for the break session use case.
 * Handles the presentation logic for break session related operations.
 */
public class BreakSessionPresenter implements BreakSessionOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private final StudySessionViewModel studySessionViewModel;

    public BreakSessionPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, StudySessionViewModel studySessionViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.studySessionViewModel = studySessionViewModel;
    }

    @Override
    public void switchToStudySessionView() {
        viewManagerModel.setState(studySessionViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToLoginView() {
        prepareLoginView();
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares LoginView.
     */
    public void prepareLoginView() {
        final LoginState currentState = loginViewModel.getState();
        // Clear logged-in user's password but not username
        currentState.setPassword("");
        currentState.setLoginError("");
        loginViewModel.setState(currentState);
        loginViewModel.firePropertyChanged();
    }
}
