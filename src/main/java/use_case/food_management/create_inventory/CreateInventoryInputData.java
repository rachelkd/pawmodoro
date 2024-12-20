package use_case.food_management.create_inventory;

/**
 * Create Inventory Use Case Input Data.
 */
public class CreateInventoryInputData {
    private final String ownerId;

    public CreateInventoryInputData(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerId() {
        return ownerId;
    }
}
