package use_case.food_management.use_item_in_inventory;

/**
 * Input Boundary for actions related to using an item in inventory.
 */
public interface UseItemInputBoundary {

    /**
     * Executes the use item use case.
     * @param useItemInputData
     */
    void execute(UseItemInputData useItemInputData);
}
