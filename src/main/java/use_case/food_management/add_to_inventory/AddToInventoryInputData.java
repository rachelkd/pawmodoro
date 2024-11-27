package use_case.food_management.add_to_inventory;

/**
 * Input data for the Add To Inventory Use Case.
 */
public class AddToInventoryInputData {
    private final String ownerId;
    private final int studySessionLength;

    public AddToInventoryInputData(String ownerId, int studySessionLength) {
        this.ownerId = ownerId;
        this.studySessionLength = studySessionLength;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public int getStudySessionLength() {
        return studySessionLength;
    }
}
