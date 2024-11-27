package use_case.food_management.add_to_inventory;

/**
 * Input data for the Add To Inventory Use Case.
 */
public class AddToInventoryInputData {
    private final String ownerId;
    private final String foodName;
    private final int studySessionLength;

    public AddToInventoryInputData(String ownerId, String foodName, int studySessionLength) {
        this.ownerId = ownerId;
        this.foodName = foodName;
        this.studySessionLength = studySessionLength;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getStudySessionLength() {
        return studySessionLength;
    }
}
