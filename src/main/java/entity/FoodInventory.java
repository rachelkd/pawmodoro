package entity;

import java.util.HashMap;
import java.util.Map;

/**
 * A user's inventory.
 */
public class FoodInventory implements Inventory {
    private final String ownerId;
    private Map<String, AbstractFood> items;

    public FoodInventory(String ownerId) {
        this.ownerId = ownerId;
        this.items = new HashMap<>();
    }

    /**
     * Returns the items in the inventory.
     *
     * @return an unmodifiable view of the items map
     */
    @Override
    public Map<String, AbstractFood> getItems() {
        // return items to pass tests, see if better way
        return new HashMap<>(items);
    }

    @Override
    public void setItems(Map<String, AbstractFood> items) {
        this.items = new HashMap<>(items);
    }

    public String getOwnerId() {
        return ownerId;
    }

}
