package use_case.food_management;

import java.util.Map;

import data_access.DBInventoryDataAccessObject;
import entity.AbstractFood;
import entity.Inventory;

/**
 * An Inventory Service that allows the program to create and use one inventory object for run of program.
 */
public final class InventoryService implements InventoryDataAccessInterface {

    private static InventoryService instance;
    private final DBInventoryDataAccessObject inventoryRepository;
    private Inventory inventory;

    private InventoryService(DBInventoryDataAccessObject inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    /**
     * Returns the Inventory Service object and ensures no duplicates.
     * @param inventoryRepository the inventory database
     * @return the inventory service object
     */
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
        return inventoryRepository.canUseItem(ownerId, foodId);
    }

    @Override
    public void save(Inventory newInventory) {
        if (inventory != null) {
            inventoryRepository.save(newInventory);
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
