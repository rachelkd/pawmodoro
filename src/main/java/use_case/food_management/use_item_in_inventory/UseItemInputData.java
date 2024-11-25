package use_case.food_management.use_item_in_inventory;

/**
 * Input data for the Use Item Use Case.
 */
public class UseItemInputData {
    private final String ownerId;
    private final String foodName;

    public UseItemInputData(String ownerId, String foodName) {
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
