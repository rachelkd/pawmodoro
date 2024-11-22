package use_case.cat_management.change_cat_hunger;

import entity.AbstractFood;

/**
 * Input Data for Change Cat Hunger Use Case.
 */
public class ChangeCatHungerInputData {
    private final String ownerUsername;
    private final String catName;
    private AbstractFood food;
    private int studySessionLength;

    public ChangeCatHungerInputData(String catName, String ownerUsername, AbstractFood food) {
        this.catName = catName;
        this.ownerUsername = ownerUsername;
        this.food = food;
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

    public AbstractFood getFood() {
        return food;
    }

    public int getStudySessionLength() {
        return studySessionLength;
    }
}
