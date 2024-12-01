package use_case.food_management.use_item_in_inventory;

import java.util.Map;

/**
 * Output Data for the Use Item Use Case.
 */
public class UseItemOutputData {
    private final Map<String, Integer> newFoodItems;
    private final String ownerId;

    public UseItemOutputData(Map<String, Integer> newFoodItems, String ownerId) {
        this.newFoodItems = newFoodItems;
        this.ownerId = ownerId;
    }

    public Map<String, Integer> getNewFoodItems() {
        return newFoodItems;
    }

    public String getOwnerId() {
        return ownerId;
    }

}
