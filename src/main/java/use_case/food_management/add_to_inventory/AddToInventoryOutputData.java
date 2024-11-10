package use_case.food_management.add_to_inventory;

/**
 * Output Data for the Add To Inventory Use Case.
 */
public class AddToInventoryOutputData {
    private final String ownerId;
    private final String foodId;

    public AddToInventoryOutputData(String ownerId, String foodId) {
        this.ownerId = ownerId;
        this.foodId = foodId;
    }

    public String getOwnerId() {return ownerId;}

    public String getFoodId() {return foodId;}
}
