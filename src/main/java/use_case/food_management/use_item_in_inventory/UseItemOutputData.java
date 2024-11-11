package use_case.food_management.use_item_in_inventory;

/**
 * Output Data for the Use Item Use Case.
 */
public class UseItemOutputData {
    private final boolean isSuccess;

    public UseItemOutputData(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean isSuccess() {return isSuccess;}

}
