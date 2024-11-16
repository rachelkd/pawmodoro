package use_case.create_inventory;

/**
 * Interface for the output boundary of the Create Inventory Use Case.
 */
public interface CreateInventoryOutputBoundary {
    /**
     * Prepares the success view for the Create Inventory Use Case.
     * 
     * @param outputData the output data (just the user)
     */
    void prepareSuccessView(CreateInventoryOutputData outputData);
}
