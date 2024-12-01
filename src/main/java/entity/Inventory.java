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
    Map<String, Integer> getItems();

    /**
     * Set the items map in to the given mapping.
     * @param items a mapping of food to quantity
     */
    void setItems(Map<String, Integer> items);

    /**
     * Gets the owner id of the inventory.
     *
     * @return the owner id string
     */
    String getOwnerId();
}
