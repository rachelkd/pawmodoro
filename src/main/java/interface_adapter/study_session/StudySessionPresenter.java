package interface_adapter.study_session;

import interface_adapter.ViewManagerModel;
import interface_adapter.break_session.BreakSessionViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.setupsession.SetupSessionViewModel;
import use_case.studysession.StudySessionOutputBoundary;
import use_case.studysession.StudySessionOutputData;

/**
 * Presenter for the study session use case.
 * Handles the presentation logic for study session related operations.
 */
public class StudySessionPresenter implements StudySessionOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private final SetupSessionViewModel setupSessionViewModel;
    private final BreakSessionViewModel breakSessionViewModel;
    private final StudySessionViewModel studySessionViewModel;

    public StudySessionPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel,
            SetupSessionViewModel setupSessionViewModel, BreakSessionViewModel breakSessionViewModel,
                                 StudySessionViewModel studySessionViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.setupSessionViewModel = setupSessionViewModel;
        this.breakSessionViewModel = breakSessionViewModel;
        this.studySessionViewModel = studySessionViewModel;
    }

    @Override
    public void switchToBreakSessionView() {
        System.out.println("Switching to Break Session View...");

        // Update the ViewManagerModel to reflect the new state
        viewManagerModel.setState(breakSessionViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToSetupSessionView() {
        viewManagerModel.setState(setupSessionViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToLoginView() {
        prepareLoginView();
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void stopStudyTimer(StudySessionOutputData studySessionOutputData) {
        final StudySessionState studySessionState = studySessionViewModel.getState();
        studySessionState.setTimerStopped();
        studySessionViewModel.setState(studySessionState);
    }

    /**
     * Prepares the login view by clearing the password and login error.
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
