package interface_adapter.add_to_inventory;

import interface_adapter.create_inventory.InventoryState;
import interface_adapter.create_inventory.InventoryViewModel;
import use_case.food_management.add_to_inventory.AddToInventoryOutputBoundary;
import use_case.food_management.add_to_inventory.AddToInventoryOutputData;

/**
 * Add To Inventory Use Case Presenter.
 */
public class AddToInventoryPresenter implements AddToInventoryOutputBoundary {
    private InventoryViewModel inventoryViewModel;

    public AddToInventoryPresenter(InventoryViewModel inventoryViewModel) {
        this.inventoryViewModel = inventoryViewModel;
    }

    @Override
    public void prepareSuccessView(AddToInventoryOutputData outputData) {
        // do nothing for now
        final InventoryState inventoryState = inventoryViewModel.getState();
        inventoryState.setOwnerId(outputData.getOwnerId());
        inventoryState.setNewFoodItem(outputData.getFood());
        inventoryViewModel.firePropertyChanged("inventory_add");
    }
}
