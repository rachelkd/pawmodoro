package use_case.food_management.use_item_in_inventory;

import java.util.Map;

import entity.AbstractFood;

/**
 * Output Data for the Use Item Use Case.
 */
public class UseItemOutputData {
    private final boolean isSuccess;
    private final Map<String, AbstractFood> newFoodItems;
    private final String ownerId;

    public UseItemOutputData(boolean isSuccess, Map<String, AbstractFood> newFoodItems, String ownerId) {

        this.isSuccess = isSuccess;
        this.newFoodItems = newFoodItems;
        this.ownerId = ownerId;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public Map<String, AbstractFood> getNewFoodItems() {
        return newFoodItems;
    }

    public String getOwnerId() {
        return ownerId;
    }

}
