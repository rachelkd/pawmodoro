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
     * @param foodId id of food item
     */
    public void execute(String ownerId, String foodId) {
        final AddToInventoryInputData addToInventoryInputData = new AddToInventoryInputData(ownerId, foodId);

        inventoryAddToInventoryUseCaseInteractor.execute(addToInventoryInputData);
    }
}
