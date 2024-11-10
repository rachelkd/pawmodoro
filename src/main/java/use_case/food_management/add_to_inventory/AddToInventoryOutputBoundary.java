package use_case.food_management.add_to_inventory;

/**
 * The output boundary for the Add to Inventory Use Case.
 */
public interface AddToInventoryOutputBoundary {

    /**
     * Prepares the success view for the Add To Inventory Use Case.
     * @param outputData the output data (just the user)
     */
    void prepareSuccessView(AddToInventoryOutputData outputData);
}
