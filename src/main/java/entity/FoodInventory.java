package entity;

import java.util.Collection;

/**
 * Represents a user's inventory of food items.
 * Different implementations can handle storage and validation differently.
 */
public interface FoodInventory {

    /**
     * Validates if a food item can be used.
     *
     * @param foodId the ID of the food to validate
     * @return true if the food exists and has quantity > 0
     * @throws IllegalArgumentException if foodId is null or empty
     */
    boolean canUseFood(String foodId);

    /**
     * Gets all food items in the inventory.
     *
     * @return the collection of food items
     */
    Collection<FoodItem> getItems();

    /**
     * Adds a food item to the inventory.
     *
     * @param item the food item to add
     * @throws IllegalArgumentException if item is null
     */
    void addItem(FoodItem item);

    /**
     * Removes one unit of the specified food from inventory.
     *
     * @param foodId the ID of the food to use
     * @return true if the food was successfully used, false if not available
     * @throws IllegalArgumentException if foodId is null or empty
     */
    boolean useFood(String foodId);
}
