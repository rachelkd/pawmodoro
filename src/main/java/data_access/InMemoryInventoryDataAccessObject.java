package data_access;

import entity.AbstractFoodItem;
import entity.Inventory;
import use_case.food_management.add_to_inventory.AddToInventoryDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryInventoryDataAccessObject implements AddToInventoryDataAccessInterface {
    private final Map<String, Inventory> inventoryStorage = new HashMap<>();

    @Override
    public void save(Inventory inventory) {
        inventoryStorage.put(inventory.getOwnerId(), inventory);
    }

    @Override
    public Inventory getInventory(String ownerId) {
        return inventoryStorage.get(ownerId);
    }

    @Override
    public void updateQuantity(String ownerId, String foodId, int quantity) {

        Inventory inventory = inventoryStorage.get(ownerId);

        if (inventory.getItems().containsKey(foodId)) {
            AbstractFoodItem item = inventory.getItems().get(foodId);
            item.setQuantity(quantity);
        }

    }
}
