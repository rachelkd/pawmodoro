package interface_adapter.adoption;

import interface_adapter.ViewManagerModel;
import interface_adapter.setupsession.SetupSessionState;
import interface_adapter.setupsession.SetupSessionViewModel;
import use_case.adoption.AdoptionOutputBoundary;
import use_case.adoption.AdoptionOutputData;

/**
 * The presenter for the adoption Use Case.
 */
public class AdoptionPresenter implements AdoptionOutputBoundary {
    private final AdoptionViewModel adoptionViewModel;
    private final SetupSessionViewModel setupSessionViewModel;
    private final ViewManagerModel viewManagerModel;

    public AdoptionPresenter(SetupSessionViewModel setupSessionViewModel, AdoptionViewModel adoptionViewModel,
                             ViewManagerModel viewManagerModel) {
        this.setupSessionViewModel = setupSessionViewModel;
        this.adoptionViewModel = adoptionViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(AdoptionOutputData response) {
        // On success, switch to the set up study session view
        final SetupSessionState setupSessionState = setupSessionViewModel.getState();
        this.setupSessionViewModel.setState(setupSessionState);
        this.setupSessionViewModel.firePropertyChanged();

        this.viewManagerModel.setState(setupSessionViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final AdoptionState adoptionState = adoptionViewModel.getState();
        adoptionState.setAdoptionError(error);
        adoptionViewModel.firePropertyChanged();
    }

    @Override
    public void switchToSetupView() {
        viewManagerModel.setState("adoption");
        viewManagerModel.firePropertyChanged();
    }
}
