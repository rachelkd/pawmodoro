package use_case.cat_management.create_cat;

import entity.Cat;

public class CreateCatOutputData {
    private final String ownerId;
    private final String catName;
    private final Cat cat;
    private final boolean isSuccess;

    public CreateCatOutputData(String ownerId, String catName, Cat cat, boolean isSuccess) {
        this.ownerId = ownerId;
        this.catName = catName;
        this.cat = cat;
        this.isSuccess = isSuccess;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getCatName() {
        return catName;
    }

    public Cat getCat() { return cat; }

    public boolean isSuccess() { return isSuccess; }
}
