package interface_adapter.use_item_in_inventory;

import interface_adapter.change_password.LoggedInViewModel;
import use_case.food_management.use_item_in_inventory.UseItemOutputBoundary;
import use_case.food_management.use_item_in_inventory.UseItemOutputData;

public class UseItemPresenter implements UseItemOutputBoundary {
    private LoggedInViewModel loggedInViewModel;

    public UseItemPresenter(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(UseItemOutputData useInventoryOutputData) {
        //do nothing for now
        loggedInViewModel.firePropertyChanged("inventory_item_used");
    }
}
