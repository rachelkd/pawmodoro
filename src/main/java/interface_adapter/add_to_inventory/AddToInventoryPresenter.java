package interface_adapter.add_to_inventory;

import interface_adapter.change_password.LoggedInViewModel;
import use_case.food_management.add_to_inventory.AddToInventoryOutputBoundary;
import use_case.food_management.add_to_inventory.AddToInventoryOutputData;

public class AddToInventoryPresenter implements AddToInventoryOutputBoundary {
    private LoggedInViewModel loggedInViewModel;

    public AddToInventoryPresenter(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
    }


    @Override
    public void prepareSuccessView(AddToInventoryOutputData outputData) {
        //do nothing for now
        loggedInViewModel.firePropertyChanged("inventory_add");
    }
}
