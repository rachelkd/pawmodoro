package interface_adapter.setupsession;

import constants.Constants;
import interface_adapter.ViewManagerModel;
import interface_adapter.break_session.BreakSessionViewModel;
import interface_adapter.study_session.StudySessionState;
import interface_adapter.study_session.StudySessionViewModel;
import interface_adapter.break_session.BreakSessionState;
import use_case.setupsession.SetupSessionOutputBoundary;
import use_case.setupsession.SetupSessionOutputData;

/**
 * Presenter for the setting up study session use case.
 */
public class SetupSessionPresenter implements SetupSessionOutputBoundary {
    private final StudySessionViewModel studySessionViewModel;
    private final BreakSessionViewModel breakSessionViewModel;
    private final ViewManagerModel viewManagerModel;

    public SetupSessionPresenter(ViewManagerModel viewManagerModel, StudySessionViewModel studySessionViewModel,
                                 BreakSessionViewModel breakSessionViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.studySessionViewModel = studySessionViewModel;
        this.breakSessionViewModel = breakSessionViewModel;
    }

    @Override
    public void prepareSuccessView(SetupSessionOutputData response) {
        // Update the StudySessionState with the selected work interval
        final StudySessionState studySessionState = studySessionViewModel.getState();
        studySessionState.setWorkInterval(response.getStudyTime() * Constants.MINUTES_TO_SECONDS
                * Constants.SECONDS_TO_MILLIS1);
        studySessionViewModel.setState(studySessionState);
        studySessionViewModel.firePropertyChanged();
        studySessionViewModel.firePropertyChanged();

        // Update the BreakSessionState with the selected break interval
        final BreakSessionState breakSessionState = breakSessionViewModel.getState();
        breakSessionState.setBreakInterval(response.getBreakTime() * Constants.MINUTES_TO_SECONDS
                * Constants.SECONDS_TO_MILLIS1);
        breakSessionViewModel.setState(breakSessionState);
        breakSessionViewModel.firePropertyChanged();

        // Now switch to study session view
        switchToStudyView();
    }

    @Override
    public void switchToStudyView() {
        viewManagerModel.setState(studySessionViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

