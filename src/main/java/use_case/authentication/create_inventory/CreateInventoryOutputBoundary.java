package use_case.authentication.create_inventory;


public interface CreateInventoryOutputBoundary {
    /**
     * Prepares the success view for the Create Inventory Use Case.
     * @param outputData the output data (just the user)
     */
    void prepareSuccessView(CreateInventoryOutputData outputData);
}
