package use_case.cat_management.change_cat_hunger;

/**
 * Input Data for Change Cat Hunger Use Case.
 */
public class ChangeCatHungerInputData {
    private final String ownerUsername;
    private final String catName;
    private String foodName;
    private int studySessionLength;

    public ChangeCatHungerInputData(String catName, String ownerUsername, String foodName) {
        this.catName = catName;
        this.ownerUsername = ownerUsername;
        this.foodName = foodName;
    }

    public ChangeCatHungerInputData(String catName, String ownerUsername, int studySessionLength) {
        this.catName = catName;
        this.ownerUsername = ownerUsername;
        this.studySessionLength = studySessionLength;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public String getCatName() {
        return catName;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getStudySessionLength() {
        return studySessionLength;
    }
}
