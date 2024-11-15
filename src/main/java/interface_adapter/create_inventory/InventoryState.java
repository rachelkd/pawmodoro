package interface_adapter.create_inventory;

import java.util.HashMap;
import java.util.Map;

import entity.AbstractFood;

/**
 * State information representing a user's inventory.
 */
public class InventoryState {
    private String ownerId;
    private Map<String, AbstractFood> inventoryItems = new HashMap<>();
    private AbstractFood newFoodItem;

    public InventoryState(InventoryState copy) {
        this.ownerId = copy.ownerId;
        this.inventoryItems = copy.inventoryItems;
        this.newFoodItem = copy.newFoodItem;
    }

    // default constructor
    public InventoryState() {
    }

    public String getOwnerId() {

        return ownerId;
    }

    public void setOwnerId(String ownerId) {

        this.ownerId = ownerId;
    }

    public Map<String, AbstractFood> getInventoryItems() {

        return inventoryItems;
    }

    public void setInventoryItems(Map<String, AbstractFood> inventoryItems) {
        this.inventoryItems = inventoryItems;
    }

    public AbstractFood getNewFoodItem() {
        return newFoodItem;
    }

    public void setNewFoodItem(AbstractFood newFoodItem) {
        this.newFoodItem = newFoodItem;
    }
}
