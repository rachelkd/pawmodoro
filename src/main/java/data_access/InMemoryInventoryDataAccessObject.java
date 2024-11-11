package data_access;

import entity.AbstractFoodItem;
import entity.Inventory;
import use_case.authentication.create_inventory.CreateInventoryInventoryDataAccessInterface;
import use_case.food_management.add_to_inventory.AddToInventoryDataAccessInterface;
import use_case.food_management.use_item_in_inventory.UseItemDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryInventoryDataAccessObject implements AddToInventoryDataAccessInterface,
        CreateInventoryInventoryDataAccessInterface, UseItemDataAccessInterface {
    private final Map<String, Inventory> inventoryStorage = new HashMap<>();

    @Override
    public void save(Inventory inventory) {
        inventoryStorage.put(inventory.getOwnerId(), inventory);
    }

    @Override
    public boolean existsByOwnerId(String ownerId) {
        return inventoryStorage.containsKey(ownerId);
    }

    @Override
    public Inventory getInventory(String ownerId) {
        return inventoryStorage.get(ownerId);
    }

    @Override
    public boolean canUseItem(String ownerId, String foodId) {
        return inventoryStorage.get(ownerId).getItems().containsKey(foodId);
    }

    @Override
    public void updateQuantity(String ownerId, String foodId, int quantity) {

        Inventory inventory = inventoryStorage.get(ownerId);

        if (inventory.getItems().containsKey(foodId)) {
            AbstractFoodItem item = inventory.getItems().get(foodId);
            item.setQuantity(quantity);
        }

    }

    public Map<String, Inventory> getInventoryStorage() {
        return inventoryStorage;
    }
}
