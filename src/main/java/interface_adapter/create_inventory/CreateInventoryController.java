package interface_adapter.create_inventory;

import use_case.authentication.create_inventory.CreateInventoryInputBoundary;
import use_case.authentication.create_inventory.CreateInventoryInputData;

/**
 * Controller for the Create Inventory use Case.
 */
public class CreateInventoryController {
    private final CreateInventoryInputBoundary inventoryCreateInventoryUseCaseInteractor;

    public CreateInventoryController(CreateInventoryInputBoundary inventoryCreateInventoryUseCaseInteractor) {
        this.inventoryCreateInventoryUseCaseInteractor = inventoryCreateInventoryUseCaseInteractor;
    }

    /**
     * Executes the Create Inventory Use Case.
     * @param ownerId
     */
    public void execute(String ownerId) {
        final CreateInventoryInputData createInventoryInputData = new CreateInventoryInputData(ownerId);
        inventoryCreateInventoryUseCaseInteractor.execute(createInventoryInputData);
    }
}
