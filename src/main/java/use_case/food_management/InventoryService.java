package use_case.food_management;

import data_access.DBInventoryDataAccessObject;
import entity.AbstractFood;
import entity.Inventory;

import java.util.Map;

public class InventoryService implements InventoryDataAccessInterface {

    private static volatile InventoryService instance;
    private final DBInventoryDataAccessObject inventoryRepository;
    private Inventory inventory;

    private InventoryService(DBInventoryDataAccessObject inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public static InventoryService getInstance(DBInventoryDataAccessObject inventoryRepository) {
        if (instance == null) {
            synchronized (InventoryService.class) {
                if (instance == null) {
                    instance = new InventoryService(inventoryRepository);
                }
            }
        }
        return instance;
    }

    @Override
    public Inventory getInventory(String ownerId) {
        if (inventory == null) {
            inventory = inventoryRepository.getInventory(ownerId);
        }
        return inventory;
    }

    @Override
    public boolean canUseItem(String ownerId, String foodId) {
        return false;
    }

    @Override
    public void save(Inventory inventory) {
        if (inventory != null) {
            inventoryRepository.save(inventory);
        }

    }

    @Override
    public boolean existsByOwnerId(String ownerId) {
        return inventoryRepository.existsByOwnerId(ownerId);
    }

    @Override
    public Map<String, AbstractFood> getInventoryItems(String ownerId) {
        if (inventory == null) {
            inventory = inventoryRepository.getInventory(ownerId);
        }
        return inventory.getItems();
    }

    @Override
    public boolean updateInventory(Inventory newInventory) {
        inventory = newInventory;

        return inventoryRepository.updateInventory(newInventory);
    }
}
