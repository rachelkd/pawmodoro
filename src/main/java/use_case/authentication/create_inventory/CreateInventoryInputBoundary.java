package use_case.authentication.create_inventory;

public interface CreateInventoryInputBoundary {

    /**
     * executes create inventory use case.
     * @param createInventoryInputData the input data
     */
    void execute(CreateInventoryInputData createInventoryInputData);
}
