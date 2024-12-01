package use_case.food_management.create_inventory;

import java.util.Map;

/**
 * Output Data for Create Inventory Use Case.
 */
public class CreateInventoryOutputData {
    private final String ownerId;
    private final boolean success;
    private final Map<String, Integer> inventoryItems;

    public CreateInventoryOutputData(String ownerId, boolean success, Map<String, Integer> inventoryItems) {
        this.ownerId = ownerId;
        this.success = success;
        this.inventoryItems = inventoryItems;
    }

    /**
     * Return whether inventory created.
     * @return bool indicating if inventory successfully created.
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Return the inventory created.
     * @return inventory of items as map
     */
    public Map<String, Integer> getInventoryItems() {
        return inventoryItems;
    }

    /**
     * Return the owner id.
     * @return the owner id
     */
    public String getOwnerId() {
        return ownerId;
    }
}
