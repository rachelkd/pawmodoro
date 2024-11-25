package interface_adapter.create_inventory;

import use_case.food_management.create_inventory.CreateInventoryOutputBoundary;
import use_case.food_management.create_inventory.CreateInventoryOutputData;

/**
 * Create Inventory Use Case Presenter.
 */
public class CreateInventoryPresenter implements CreateInventoryOutputBoundary {
    private final InventoryViewModel inventoryViewModel;

    public CreateInventoryPresenter(InventoryViewModel inventoryViewModel) {
        this.inventoryViewModel = inventoryViewModel;
    }

    @Override
    public void prepareSuccessView(CreateInventoryOutputData outputData) {
        final InventoryState inventoryState = inventoryViewModel.getState();

        inventoryState.setOwnerId(outputData.getOwnerId());
        inventoryState.setInventoryItems(outputData.getInventoryItems());

        this.inventoryViewModel.setState(inventoryState);
        this.inventoryViewModel.firePropertyChanged("inventory_created");

    }
}
