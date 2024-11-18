package use_case.cat_management.change_cat_hunger;

import entity.AbstractFood;
import entity.Cat;

public class ChangeCatHungerInputData {
    private final String ownerUsername;
    private final String catName;
    private final Cat cat;
    private AbstractFood food;
    private int studySessionLength;

    public ChangeCatHungerInputData(String catName, String ownerUsername, Cat cat, AbstractFood food) {
        this.catName = catName;
        this.ownerUsername = ownerUsername;
        this.cat = cat;
        this.food = food;
    }

    public ChangeCatHungerInputData(String catName, String ownerUsername, Cat cat, int studySessionLength) {
        this.catName = catName;
        this.ownerUsername = ownerUsername;
        this.cat = cat;
        this.studySessionLength = studySessionLength;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public String getCatName() {
        return catName;
    }

    public Cat getCat() {
        return cat;
    }

    public AbstractFood getFood() {
        return food;
    }

    public int getStudySessionLength() { return studySessionLength; }
}
