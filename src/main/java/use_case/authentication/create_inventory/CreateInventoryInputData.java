package use_case.authentication.create_inventory;

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
