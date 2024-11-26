package use_case.food_management.create_inventory;

/**
 * Input boundry of the Create Inventory Use Case.
 */
public interface CreateInventoryInputBoundary {

    /**
     * Executes create inventory use case.
     * @param createInventoryInputData the input data
     */
    void execute(CreateInventoryInputData createInventoryInputData);
}
