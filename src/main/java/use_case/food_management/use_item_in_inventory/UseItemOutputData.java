package use_case.food_management.use_item_in_inventory;

import java.util.Map;

import entity.AbstractFood;

/**
 * Output Data for the Use Item Use Case.
 */
public class UseItemOutputData {
    private final Map<String, AbstractFood> newFoodItems;
    private final String ownerId;

    public UseItemOutputData(Map<String, AbstractFood> newFoodItems, String ownerId) {
        this.newFoodItems = newFoodItems;
        this.ownerId = ownerId;
    }

    public Map<String, AbstractFood> getNewFoodItems() {
        return newFoodItems;
    }

    public String getOwnerId() {
        return ownerId;
    }

}
