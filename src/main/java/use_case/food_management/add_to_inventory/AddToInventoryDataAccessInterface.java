package use_case.food_management.add_to_inventory;

import entity.Inventory;

/**
 * Repository interface for managing food inventory persistence.
 * This interface defines the contract for storing and retrieving food items
 * associated with users.
 */
public interface AddToInventoryDataAccessInterface {

    /**
     * Updates inventory in the repository. Returns true if updated.
     *
     * @param inventory
     *            the inventory to update
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
}
