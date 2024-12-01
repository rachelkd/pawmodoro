package use_case.food_management.add_to_inventory;

import java.util.Map;

import entity.AbstractFood;

/**
 * Output Data for the Add To Inventory Use Case.
 */
public class AddToInventoryOutputData {
    private final String ownerId;
    private final AbstractFood food;
    private final Map<String, AbstractFood> foodItems;

    public AddToInventoryOutputData(String ownerId, AbstractFood food, Map<String, AbstractFood> foodItems) {
        this.ownerId = ownerId;
        this.food = food;
        this.foodItems = foodItems;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public AbstractFood getFood() {
        return food;
    }

    public Map<String, AbstractFood> getFoodItems() {
        return foodItems;
    }
}
