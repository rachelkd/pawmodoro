package use_case.authentication.create_inventory;

import entity.Inventory;

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

    boolean existsByOwnerId(String ownerId);

}
