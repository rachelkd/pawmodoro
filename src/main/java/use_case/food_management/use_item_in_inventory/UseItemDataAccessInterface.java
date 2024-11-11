package use_case.food_management.use_item_in_inventory;

import entity.Inventory;

/**
 * DAO for teh Add Item Use Case
 */
public interface UseItemDataAccessInterface {
    /**
     * Saves inventory to the repository.
     * If the inventory already exists, it will be updated.
     *
     * @param inventory
     *            the inventory to save
     * @throws IllegalArgumentException
     *             if inventory is null
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
     * Removes one of a specific food from the inventory of the user.
     * @param ownerId the ID of the ownerof food items
     * @param foodId the ID  of the food item to use
     */

    boolean canUseItem(String ownerId, String foodId);
}
