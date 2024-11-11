package use_case.food_management.add_to_inventory;

/**
 * Input Boundary for actions related to adding to the inventory.
 */
public interface AddToInventoryInputBoundary {

    /**
     * Executes add to inventory use case.
     * @param addToInventoryInputData the input data
     */
    void execute(AddToInventoryInputData addToInventoryInputData);
}
