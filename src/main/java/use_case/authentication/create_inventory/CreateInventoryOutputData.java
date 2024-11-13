package use_case.authentication.create_inventory;

import entity.AbstractFood;

import java.util.Map;

/**
 * Create Inventory Use Case.
 */
public class CreateInventoryOutputData {
    private final boolean success;
    private final Map<String, AbstractFood> inventoryItems;

    public CreateInventoryOutputData(boolean success, Map<String, AbstractFood> inventoryItems) {
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
}
