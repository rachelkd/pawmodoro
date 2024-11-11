package interface_adapter.setupsession;

import interface_adapter.ViewManagerModel;
import use_case.setupsession.SetupSessionOutputBoundary;

/**
 * TODO: Check correctness
 * Presenter for the setting up study session use case.
 */
public class SetupSessionPresenter implements SetupSessionOutputBoundary {
    private final SetupSessionViewModel setupSessionViewModel;
    private final ViewManagerModel viewManagerModel;

    public SetupSessionPresenter(SetupSessionViewModel setupSessionViewModel,
            ViewManagerModel viewManagerModel) {
        this.setupSessionViewModel = setupSessionViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView() {
        // When success, switch to study timer view.
        final SetupSessionState currentState = setupSessionViewModel.getState();
        setupSessionViewModel.setState(currentState);
        setupSessionViewModel.firePropertyChanged();
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
