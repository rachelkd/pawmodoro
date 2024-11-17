package use_case.cat_management.create_cat;

/**
 * Input Data of Create Cat Use Case.
 */
public class CreateCatInputData {
    private final String ownerId;
    private final String catName;

    public CreateCatInputData(String ownerId, String catName) {
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
