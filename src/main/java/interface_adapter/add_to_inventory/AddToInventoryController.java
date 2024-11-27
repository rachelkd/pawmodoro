package interface_adapter.add_to_inventory;

import use_case.food_management.add_to_inventory.AddToInventoryInputBoundary;
import use_case.food_management.add_to_inventory.AddToInventoryInputData;

/**
 * Controller for the Add To Inventory Use Case.
 */
public class AddToInventoryController {
    private final AddToInventoryInputBoundary inventoryAddToInventoryUseCaseInteractor;

    public AddToInventoryController(AddToInventoryInputBoundary inventoryAddToInventoryUseCaseInteractor) {
        this.inventoryAddToInventoryUseCaseInteractor = inventoryAddToInventoryUseCaseInteractor;
    }

    /**
     * Executes the Add to Inventory Use Case.
     * @param ownerId id of owner
     * @param studySessionLength
     */
    public void execute(String ownerId, int studySessionLength) {
        final AddToInventoryInputData addToInventoryInputData = new AddToInventoryInputData(ownerId, studySessionLength);

        inventoryAddToInventoryUseCaseInteractor.execute(addToInventoryInputData);
    }
}
