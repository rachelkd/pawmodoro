package interface_adapter.adoption;

import interface_adapter.setupsession.SetupSessionViewModel;

/**
 * The presenter for the adoption Use Case.
 */
public class AdoptionPresenter {
    private final SetupSessionViewModel setUpSessionViewModel;

    public AdoptionPresenter(SetupSessionViewModel setUpSessionViewModel) {
        this.setUpSessionViewModel = setUpSessionViewModel;
    }

    public void prepareSuccessView() {
        // TODO: Implement this method
    }

    public void prepareFailView() {
        // TODO: Implement this method
    }
}
