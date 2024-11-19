package use_case.create_inventory;

import java.util.Map;

import entity.AbstractFood;

/**
 * Output Data for Create Inventory Use Case.
 */
public class CreateInventoryOutputData {
    private final String ownerId;
    private final boolean success;
    private final Map<String, AbstractFood> inventoryItems;

    public CreateInventoryOutputData(String ownerId, boolean success, Map<String, AbstractFood> inventoryItems) {
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
    public Map<String, AbstractFood> getInventoryItems() {
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
