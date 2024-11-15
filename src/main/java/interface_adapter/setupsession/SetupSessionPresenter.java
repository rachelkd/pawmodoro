package interface_adapter.setupsession;

import interface_adapter.ViewManagerModel;
import interface_adapter.timer.TimerViewModel;
import interface_adapter.timer.TimerState;
import use_case.setupsession.SetupSessionOutputBoundary;
import use_case.setupsession.SetupSessionOutputData;

/**
 * TODO: Check correctness
 * Presenter for the setting up study session use case.
 */
public class SetupSessionPresenter implements SetupSessionOutputBoundary {
    private final SetupSessionViewModel setupSessionViewModel;
    private final TimerViewModel timerViewModel;
    private final ViewManagerModel viewManagerModel;

    public SetupSessionPresenter(SetupSessionViewModel setupSessionViewModel,
            ViewManagerModel viewManagerModel, TimerViewModel timerViewModel) {
        this.setupSessionViewModel = setupSessionViewModel;
        this.viewManagerModel = viewManagerModel;
        this.timerViewModel = timerViewModel;
    }

    @Override
    public void prepareSuccessView(SetupSessionOutputData response) {
        // When success, switch to study timer view.
        final TimerState timerState = timerViewModel.getState();
        timerState.setIntervalDuration(response.getStudyTime());
        this.timerViewModel.setState(timerState);
        timerViewModel.firePropertyChanged();

        viewManagerModel.setState(timerViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView() {
        // TODO: Implement this method
    }

    @Override
    public void switchToStudyView() {
        viewManagerModel.setState(setupSessionViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
