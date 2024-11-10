package use_case.food_management.add_to_inventory;

import entity.Inventory;

/**
 * Repository interface for managing food inventory persistence.
 * This interface defines the contract for storing and retrieving food items
 * associated with users.
 */
public interface AddToInventoryDataAccessInterface {

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
     * Updates the quantity of a specific food item for a user.
     *
     * @param ownerId
     *            the ID of the owner of the food item
     * @param foodId
     *            the ID of the food item to update
     * @param quantity
     *            the new quantity to set
     * @throws IllegalArgumentException
     *             if ownerId or foodId is null or empty,
     *             or if quantity is negative
     */
    void updateQuantity(String ownerId, String foodId, int quantity);
}
