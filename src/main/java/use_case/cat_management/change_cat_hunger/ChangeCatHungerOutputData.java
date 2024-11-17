package use_case.cat_management.change_cat_hunger;

import entity.Cat;

public class ChangeCatHungerOutputData {
    private final String ownerId;
    private final Cat cat;
    private final int newHungerLevel;

    public ChangeCatHungerOutputData(String ownerId, Cat cat, int newHungerLevel) {
        this.ownerId = ownerId;
        this.cat = cat;
        this.newHungerLevel = newHungerLevel;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public Cat getCat() {
        return cat;
    }

    public int getNewHungerLevel() {
        return newHungerLevel;
    }
}
