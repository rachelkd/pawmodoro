package use_case.food_management.use_item_in_inventory;

import java.util.Map;

import entity.AbstractFood;

/**
 * Output Data for the Use Item Use Case.
 */
public class UseItemOutputData {
    private final boolean isSuccess;
    private Map<String, AbstractFood> newFoodItems;

    public UseItemOutputData(boolean isSuccess, Map<String, AbstractFood> newFoodItems) {

        this.isSuccess = isSuccess;
        this.newFoodItems = newFoodItems;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public Map<String, AbstractFood> getNewFoodItems() {
        return newFoodItems;
    }

}
