package interface_adapter.create_inventory;

import interface_adapter.change_password.LoggedInViewModel;
import use_case.authentication.create_inventory.CreateInventoryOutputBoundary;
import use_case.authentication.create_inventory.CreateInventoryOutputData;

/**
 * Create Inventory Use Case Presenter.
 */
public class CreateInventoryPresenter implements CreateInventoryOutputBoundary {
    private LoggedInViewModel loggedInViewModel;

    public CreateInventoryPresenter(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(CreateInventoryOutputData outputData) {
        // do nothing for now
        loggedInViewModel.firePropertyChanged("inventory_created");
    }
}
