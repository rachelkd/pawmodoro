package entity;

import java.util.Map;

/**
 * Represents a user's inventory of food items.
 * Different implementations can handle storage and validation differently.
 */
public interface Inventory {

    /**
     * Gets the inventory of food items.
     *
     * @return the map of food items to the user's quantity
     */
    Map<String, AbstractFoodItem> getItems();

    /**
     * Gets the owner id of the inventory.
     *
     * @return the owner id string
     */
    String getOwnerId();

}
