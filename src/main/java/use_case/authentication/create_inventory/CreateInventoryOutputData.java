package use_case.authentication.create_inventory;

/**
 * Create Inventory Use Case.
 */
public class CreateInventoryOutputData {
    private final boolean success;

    public CreateInventoryOutputData(boolean success) {
        this.success = success;
    }

    /**
     * Return whether inventory created.
     * @return bool indicating if inventory successfully created.
     */
    public boolean isSuccess() {
        return success;
    }
}
