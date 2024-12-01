package interface_adapter.use_item_in_inventory;

import interface_adapter.create_inventory.InventoryState;
import interface_adapter.create_inventory.InventoryViewModel;
import use_case.food_management.use_item_in_inventory.UseItemOutputBoundary;
import use_case.food_management.use_item_in_inventory.UseItemOutputData;

/**
 * Use Item Use Case Presenter.
 */
public class UseItemPresenter implements UseItemOutputBoundary {
    private final InventoryViewModel inventoryViewModel;

    public UseItemPresenter(InventoryViewModel inventoryViewModel) {
        this.inventoryViewModel = inventoryViewModel;
    }

    @Override
    public void prepareSuccessView(UseItemOutputData useInventoryOutputData) {
        final InventoryState inventoryState = inventoryViewModel.getState();
        inventoryState.setInventoryItems(useInventoryOutputData.getNewFoodItems());
        inventoryState.setOwnerId(useInventoryOutputData.getOwnerId());
        inventoryViewModel.firePropertyChanged("inventory_item_used");
    }
}
