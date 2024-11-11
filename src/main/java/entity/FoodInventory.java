package entity;

import java.util.HashMap;
import java.util.Map;

public class FoodInventory implements Inventory {
    private final String ownerId;
    private final Map<String,AbstractFoodItem> items;

    public FoodInventory(String ownerId) {
        this.ownerId = ownerId;
        this.items = new HashMap<>();
    }

    public Map<String, AbstractFoodItem> getItems() {
        return items;
    }

    public String getOwnerId() {
        return ownerId;
    }

}
