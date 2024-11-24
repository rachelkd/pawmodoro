package use_case.food_management;

import java.util.Map;

import entity.AbstractFood;
import entity.Inventory;

/**
 * Repository interface for managing food inventory persistence.
 * This interface defines the contract for storing and retrieving food items
 * associated with users.
 */
public interface InventoryDataAccessInterface {

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

    /**
     * Updates inventory in the repository. Returns true if updated.
     *
     * @param inventory
     *            the inventory to update
     * @return boolean of whether successfully updated
     * @throws IllegalArgumentException
     *             if inventory is null
     */
    boolean updateInventory(Inventory inventory);

    /**
     * Retrieves the inventory of food items owned by a specific user.
     *
     * @param ownerId
     *            the ID of the owner whose food items to retrieve
     * @return a map of food items owned by the user
     * @throws IllegalArgumentException
     *             if ownerId is null or empty
     */
    Inventory getInventory(String ownerId);

    /**
     * Removes one of a specific food from the inventory of the user.
     * @param ownerId the ID of the ownerof food items
     * @param foodId the ID  of the food item to use
     * @return bool of whether you can use items
     */

    boolean canUseItem(String ownerId, String foodId);
}
