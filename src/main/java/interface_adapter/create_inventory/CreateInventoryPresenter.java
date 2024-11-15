package interface_adapter.create_inventory;

import interface_adapter.ViewManagerModel;
import use_case.authentication.create_inventory.CreateInventoryOutputBoundary;
import use_case.authentication.create_inventory.CreateInventoryOutputData;

/**
 * Create Inventory Use Case Presenter.
 */
public class CreateInventoryPresenter implements CreateInventoryOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private InventoryViewModel inventoryViewModel;

    public CreateInventoryPresenter(ViewManagerModel viewManagerModel, InventoryViewModel inventoryViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.inventoryViewModel = inventoryViewModel;
    }

    @Override
    public void prepareSuccessView(CreateInventoryOutputData outputData) {
        final InventoryState inventoryState = inventoryViewModel.getState();
        inventoryState.setOwnerId(outputData.getOwnerId());
        inventoryState.setInventoryItems(outputData.getInventoryItems());

        this.inventoryViewModel.setState(inventoryState);
        this.inventoryViewModel.firePropertyChanged("inventory_created");

        // this.viewManagerModel.setState(inventoryViewModel.getViewName());
        // this.viewManagerModel.firePropertyChanged();

    }
}
