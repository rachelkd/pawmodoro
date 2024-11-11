package use_case.authentication.create_inventory;

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
