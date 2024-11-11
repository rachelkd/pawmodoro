package use_case.food_management.add_to_inventory;

/**
 * Input data for the Add To Inventory Use Case.
 */
public class AddToInventoryInputData {
    private final String ownerId;
    private final String foodId;

    public AddToInventoryInputData(String ownerId, String foodId) {
        this.ownerId = ownerId;
        this.foodId = foodId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getFoodId() {
        return foodId;
    }
}
