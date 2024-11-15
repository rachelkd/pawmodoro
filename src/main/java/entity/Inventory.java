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
    Map<String, AbstractFood> getItems();

    /**
     * Set the items map in to the given mapping.
     * @param items a mapping of food ids to food objects
     */
    void setItems(Map<String, AbstractFood> items);

    /**
     * Gets the owner id of the inventory.
     *
     * @return the owner id string
     */
    String getOwnerId();
}
