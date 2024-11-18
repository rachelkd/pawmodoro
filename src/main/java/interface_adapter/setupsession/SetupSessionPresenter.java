package interface_adapter.setupsession;

import interface_adapter.ViewManagerModel;
import interface_adapter.study_session.StudySessionState;
import interface_adapter.study_session.StudySessionViewModel;
import interface_adapter.timer.TimerState;
import interface_adapter.timer.TimerViewModel;
import use_case.setupsession.SetupSessionOutputBoundary;
import use_case.setupsession.SetupSessionOutputData;

/**
 * TODO: Check correctness
 * Presenter for the setting up study session use case.
 */
public class SetupSessionPresenter implements SetupSessionOutputBoundary {
    private final StudySessionViewModel studySessionViewModel;
    private final ViewManagerModel viewManagerModel;

    public SetupSessionPresenter(ViewManagerModel viewManagerModel, StudySessionViewModel studySessionViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.studySessionViewModel = studySessionViewModel;
    }

    @Override
    public void prepareSuccessView(SetupSessionOutputData response) {
        // When success, switch to study session view.
        final StudySessionState studySessionState = studySessionViewModel.getState();
        this.studySessionViewModel.setState(studySessionState);
        studySessionViewModel.firePropertyChanged();

        viewManagerModel.setState(studySessionViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

//    @Override
//    public void prepareFailView() {
//        // TODO: Implement this method
//    }

    @Override
    public void switchToStudyView() {
        viewManagerModel.setState(studySessionViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
