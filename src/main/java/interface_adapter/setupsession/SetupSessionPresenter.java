package interface_adapter.setupsession;

import interface_adapter.ViewManagerModel;
import interface_adapter.study_session.StudySessionState;
import interface_adapter.study_session.StudySessionViewModel;
import use_case.setupsession.SetupSessionOutputBoundary;
import use_case.setupsession.SetupSessionOutputData;

/**
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

    @Override
    public void switchToStudyView() {
        viewManagerModel.setState(studySessionViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
