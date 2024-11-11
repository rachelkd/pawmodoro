package interface_adapter.maxcatserror;

import interface_adapter.ViewManagerModel;
import interface_adapter.setupsession.SetupSessionState;
import interface_adapter.setupsession.SetupSessionViewModel;
import use_case.maxcatserror.MaxCatsErrorOutputBoundary;

/**
 * The presenter for the maximum cats reached error use case.
 */
public class MaxCatsErrorPresenter implements MaxCatsErrorOutputBoundary {
    private final MaxCatsErrorViewModel maxCatsViewModel;
    // The name of the view model for setting up study session
    private final SetupSessionViewModel setupSessionViewModel;
    private final ViewManagerModel viewManagerModel;

    public MaxCatsErrorPresenter(ViewManagerModel viewManagerModel,
            SetupSessionViewModel setupViewModel,
            MaxCatsErrorViewModel maxCatsErrorViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.maxCatsViewModel = maxCatsErrorViewModel;
        this.setupSessionViewModel = setupViewModel;
    }

    @Override
    public void prepareSuccessView() {
        // Switch back to setting up study session view.
        final SetupSessionState setupState = setupSessionViewModel.getState();
        this.setupSessionViewModel.setState(setupState);
        setupSessionViewModel.firePropertyChanged();

        viewManagerModel.setState(setupSessionViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToSetupView() {
        viewManagerModel.setState(setupSessionViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
