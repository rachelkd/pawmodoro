package entity;

/**
* Factory for building Inventory objects.
 */
public class FoodInventoryFactory implements InventoryFactory {

    /**
     * Creates a new Inventory for user.
     * @param ownerId the id of the current user
     * @return the new inventory
     */
    @Override
    public Inventory create(String ownerId) {
        return new FoodInventory(ownerId);
    }
}
