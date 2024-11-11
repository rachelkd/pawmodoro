package entity;

import java.util.HashMap;
import java.util.Map;

public class FoodInventory implements Inventory {
    private final String ownerId;
    private final Map<String, AbstractFood> items;

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
        return Map.copyOf(items);
    }

    public String getOwnerId() {
        return ownerId;
    }

}
