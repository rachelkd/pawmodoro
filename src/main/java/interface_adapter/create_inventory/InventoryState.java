package interface_adapter.create_inventory;

import java.util.HashMap;
import java.util.Map;

import entity.AbstractFood;

/**
 * State information representing a user's inventory.
 */
public class InventoryState {
    private String ownerId;
    private Map<String, AbstractFood> inventory = new HashMap<>();

    public InventoryState(InventoryState copy) {
        this.ownerId = copy.ownerId;
        this.inventory = copy.inventory;
    }

    // default constructor
    public InventoryState() {}

    public String getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Map<String, AbstractFood> getInventory() {
        return inventory;
    }

    public void setInventory(Map<String, AbstractFood> inventory) {
        this.inventory = inventory;
    }
}
