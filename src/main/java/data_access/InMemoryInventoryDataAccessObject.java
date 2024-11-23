package data_access;

import java.util.HashMap;
import java.util.Map;

import entity.AbstractFood;
import entity.FoodInventory;
import entity.Inventory;
import use_case.food_management.create_inventory.CreateInventoryInventoryDataAccessInterface;
import use_case.food_management.add_to_inventory.AddToInventoryDataAccessInterface;
import use_case.food_management.use_item_in_inventory.UseItemDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing user's inventory data. This
 * implementation does
 * NOT persist data between runs of the program.
 */
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
    public Map<String, AbstractFood> getInventoryItems(String ownerId) {
        return new HashMap<>(inventoryStorage.get(ownerId).getItems());
    }

    @Override
    public Inventory getInventory(String ownerId) {
        // for testing purposes return a deep copy
        Inventory inventory = inventoryStorage.get(ownerId);
        Inventory copyInventory = new FoodInventory(inventory.getOwnerId());

        // for loop through items?

        copyInventory.setItems(inventory.getItems());

        return copyInventory;
    }

    @Override
    public boolean canUseItem(String ownerId, String foodId) {
        return inventoryStorage.get(ownerId).getItems().containsKey(foodId);
    }

    @Override
    public boolean updateInventory(Inventory inventory) {
        if (existsByOwnerId(inventory.getOwnerId())) {
            inventoryStorage.put(inventory.getOwnerId(), inventory);
            return true;
        }
        return false;
    }

    /**
     * Return whether the user's inventory is empty.
     * @param ownerId the owner id
     * @return a boolean indicating if inventory is empty.
     */
    public boolean isEmpty(String ownerId) {
        return inventoryStorage.get(ownerId).getItems().isEmpty();
    }
}
