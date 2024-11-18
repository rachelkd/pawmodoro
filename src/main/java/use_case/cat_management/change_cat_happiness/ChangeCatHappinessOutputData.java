package use_case.cat_management.change_cat_happiness;

/**
 * Output Data for Change Happiness Use Case.
 */
public class ChangeCatHappinessOutputData {
    private final String ownerId;
    private final String catName;
    private final int newHappinessLevel;

    public ChangeCatHappinessOutputData(String ownerId, String catName, int newHappinessLevel) {
        this.ownerId = ownerId;
        this.catName = catName;
        this.newHappinessLevel = newHappinessLevel;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getCatName() {
        return catName;
    }

    public int getNewHappinessLevel() {
        return newHappinessLevel;
    }
}
