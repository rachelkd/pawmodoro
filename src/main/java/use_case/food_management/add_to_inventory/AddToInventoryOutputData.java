package use_case.food_management.add_to_inventory;

import entity.AbstractFood;

/**
 * Output Data for the Add To Inventory Use Case.
 */
public class AddToInventoryOutputData {

    private final boolean success;
    private final String ownerId;
    private final AbstractFood foodItem;

    public AddToInventoryOutputData(boolean success, String ownerId, AbstractFood foodItem) {

        this.success = success;
        this.ownerId = ownerId;
        this.foodItem = foodItem;
    }

    public boolean isSuccess() {

        return success;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public AbstractFood getFoodItem() {
        return foodItem;
    }
}
