package data_access;

import java.util.HashMap;
import java.util.Map;

import entity.Inventory;
import use_case.food_management.InventoryDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing user's inventory data. This
 * implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryInventoryDataAccessObject implements InventoryDataAccessInterface {
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
    public Map<String, Integer> getInventoryItems(String ownerId) {
        return new HashMap<>(inventoryStorage.get(ownerId).getItems());
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
    public boolean updateInventory(Inventory inventory) {
        boolean isSuccess = false;
        if (existsByOwnerId(inventory.getOwnerId())) {
            inventoryStorage.put(inventory.getOwnerId(), inventory);
            isSuccess = true;
        }
        return isSuccess;
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
