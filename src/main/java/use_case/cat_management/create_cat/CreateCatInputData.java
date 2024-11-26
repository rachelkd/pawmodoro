package use_case.cat_management.create_cat;

/**
 * Input Data of Create Cat Use Case.
 */
public class CreateCatInputData {
    private final String catName;
    private final String ownerUsername;

    public CreateCatInputData(String catName, String ownerUsername) {
        this.catName = catName;
        this.ownerUsername = ownerUsername;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public String getCatName() {
        return catName;
    }
}
