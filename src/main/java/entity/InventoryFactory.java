package entity;

/**
Factory for creating Inventories
 */
public interface InventoryFactory {

    /**
     * Creates a new Inventory for user.
     * @param ownerId the id of the current user
     * @return the new inventory
     */
    Inventory create(String ownerId);
}
