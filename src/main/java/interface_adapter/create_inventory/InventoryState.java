package interface_adapter.create_inventory;

import java.util.HashMap;
import java.util.Map;

/**
 * State information representing a user's inventory.
 */
public class InventoryState {
    private String ownerId;
    private Map<String, Integer> inventoryItems = new HashMap<>();
    private String currentFoodName;
    private String currentCatName;

    public InventoryState(InventoryState copy) {
        this.ownerId = copy.ownerId;
        this.inventoryItems = copy.inventoryItems;
        this.currentFoodName = copy.getCurrentFoodName();
        this.currentCatName = copy.currentCatName;
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

    public Map<String, Integer> getInventoryItems() {

        return inventoryItems;
    }

    public void setInventoryItems(Map<String, Integer> inventoryItems) {
        this.inventoryItems = inventoryItems;
    }

    public String getCurrentFoodName() {
        return currentFoodName;
    }

    public void setCurrentFoodName(String newFoodName) {
        this.currentFoodName = newFoodName;
    }

    public String getCurrentCatName() {
        return currentCatName;
    }

    public void setCurrentCatName(String currentCatName) {
        this.currentCatName = currentCatName;
    }
}
