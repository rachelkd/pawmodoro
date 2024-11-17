package use_case.cat_management.change_cat_hunger;

import entity.AbstractFood;
import entity.Cat;

public class ChangeCatHungerInputData {
    private final String ownerId;
    private final String catName;
    private final Cat cat;
    private final AbstractFood food;

    public ChangeCatHungerInputData(String ownerId, String catName, Cat cat, AbstractFood food) {
        this.ownerId = ownerId;
        this.catName = catName;
        this.cat = cat;
        this.food = food;
    }

    public String getOwnerId() {
        return ownerId;
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
}
