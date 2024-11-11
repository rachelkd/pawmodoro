package interface_adapter.adoption;


/**
 * The presenter for the adoption Use Case.
 */
public class AdoptionPresenter {
    private final SetupSessionViewModel setUpSessionViewModel;

    public void prepareSuccessView() {
        setupSessionViewMode.firePropertyChanged("confirm");
    }

    public void prepareFailView() {

    }


}
