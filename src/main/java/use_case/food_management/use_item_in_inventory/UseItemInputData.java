package use_case.food_management.use_item_in_inventory;

/**
 * Input data for the Use Item Use Case.
 */
public class UseItemInputData {
    private final String ownerId;
    private final String foodId;

    public UseItemInputData(String ownerId, String foodId) {
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
