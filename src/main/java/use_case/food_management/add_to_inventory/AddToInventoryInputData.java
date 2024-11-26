package use_case.food_management.add_to_inventory;

/**
 * Input data for the Add To Inventory Use Case.
 */
public class AddToInventoryInputData {
    private final String ownerId;
    private final String foodName;

    public AddToInventoryInputData(String ownerId, String foodName) {
        this.ownerId = ownerId;
        this.foodName = foodName;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getFoodName() {
        return foodName;
    }
}
