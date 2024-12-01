package use_case.food_management.add_to_inventory;

import java.util.Map;

/**
 * Output Data for the Add To Inventory Use Case.
 */
public class AddToInventoryOutputData {
    private final String ownerId;
    private final String foodName;
    private final Map<String, Integer> foodItems;

    public AddToInventoryOutputData(String ownerId, String foodName, Map<String, Integer> foodItems) {
        this.ownerId = ownerId;
        this.foodName = foodName;
        this.foodItems = foodItems;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getFoodName() {
        return foodName;
    }

    public Map<String, Integer> getFoodItems() {
        return foodItems;
    }
}
