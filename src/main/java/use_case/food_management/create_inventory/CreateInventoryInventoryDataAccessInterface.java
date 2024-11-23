package use_case.food_management.create_inventory;

import java.util.Map;

import entity.AbstractFood;
import entity.Inventory;

/**
 * DAO for the Create Inventory Use Case.
 */
public interface CreateInventoryInventoryDataAccessInterface {
    /**
     * Saves inventory to the repository.
     * If the inventory already exists, it will be updated.
     *
     * @param inventory
     *            the inventory to save
     * @throws IllegalArgumentException
     *             if item is null
     */
    void save(Inventory inventory);

    /**
     * Return True if owner is already in inventory repository.
     * @param ownerId the id of the owner/user
     * @return a boolean indicating whether the user is in the current repository of inventories
     */
    boolean existsByOwnerId(String ownerId);

    /**
     * Return the user's items as map.
     * @param ownerId the owner's id
     * @return a map of food ids to food objects
     */
    Map<String, AbstractFood> getInventoryItems(String ownerId);

}
