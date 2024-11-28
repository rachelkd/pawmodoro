package use_case.cat_management.change_cat_hunger;

/**
 * Output Data for Change Cat Hunger Use Case.
 */
public class ChangeCatHungerOutputData {
    private final String ownerId;
    private final String catName;
    private final int newHungerLevel;

    public ChangeCatHungerOutputData(String ownerId, String catName, int newHungerLevel) {
        this.ownerId = ownerId;
        this.catName = catName;
        this.newHungerLevel = newHungerLevel;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public int getNewHungerLevel() {
        return newHungerLevel;
    }

    public String getCatName() {
        return catName;
    }
}
