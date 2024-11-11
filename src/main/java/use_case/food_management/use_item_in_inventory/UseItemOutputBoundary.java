package use_case.food_management.use_item_in_inventory;

/**
 * The output boundary for the Use item Use Case.
 */
public interface UseItemOutputBoundary {

    /**
     * Prepares the success view for the Use Item Use Case.
     * @param useInventoryOutputData the output data
     */
    void prepareSuccessView(UseItemOutputData useInventoryOutputData);
}
