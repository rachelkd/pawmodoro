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


    public AdoptionPresenter(AdoptionViewModel adoptionViewModel) {
        this.adoptionViewModel = adoptionViewModel;
    }

    @Override
    public void prepareSuccessView(AdoptionOutputData response) {
        final AdoptionState adoptionState = adoptionViewModel.getState();
        adoptionState.setCatName(response.getCatName());
        adoptionState.setOwner(response.getOwnerName());

        this.adoptionViewModel.setState(adoptionState);
    }

    @Override
    public void prepareFailView(String error) {
        final AdoptionState adoptionState = adoptionViewModel.getState();
        adoptionState.setAdoptionError(error);
        adoptionViewModel.firePropertyChanged();
    }

}
