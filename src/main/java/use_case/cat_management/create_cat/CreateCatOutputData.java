package use_case.cat_management.create_cat;

public class CreateCatOutputData {
    private final String ownerId;
    private final String catName;

    public CreateCatOutputData(String ownerId, String catName) {
        this.ownerId = ownerId;
        this.catName = catName;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getCatName() {
        return catName;
    }
}
