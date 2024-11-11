package interface_adapter.use_item_in_inventory;

import use_case.food_management.use_item_in_inventory.UseItemInputBoundary;
import use_case.food_management.use_item_in_inventory.UseItemInputData;

/**
 * Controller for the Use Item Use Case
 */
public class UseItemController {
    private final UseItemInputBoundary inventoryUseItemUseCaseInteractor;

    public UseItemController(UseItemInputBoundary inventoryUseItemUseCaseInteractor) {
        this.inventoryUseItemUseCaseInteractor = inventoryUseItemUseCaseInteractor;
    }

    /**
     * Executes the Use Item Use Case.
     * @param ownerId
     * @param foodId
     */
    public void execute(String ownerId, String foodId) {
        final UseItemInputData useItemInputData = new UseItemInputData(ownerId, foodId);
        inventoryUseItemUseCaseInteractor.execute(useItemInputData);
    }
}
