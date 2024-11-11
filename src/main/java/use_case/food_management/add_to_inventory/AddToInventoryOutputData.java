package use_case.food_management.add_to_inventory;

/**
 * Output Data for the Add To Inventory Use Case.
 */
public class AddToInventoryOutputData {

    private final boolean success;

    public AddToInventoryOutputData(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
