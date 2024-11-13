package use_case.authentication.create_inventory;

import entity.AbstractFood;
import entity.Inventory;

import java.util.Map;

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

    Map<String, AbstractFood> getInventoryItems(String ownerId);

}
