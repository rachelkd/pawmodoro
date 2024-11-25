package use_case.adoption;

/**
 * The Input Data for the Adoption Use Case
 */
public class AdoptionInputData {
    private final String catName;
    private final String ownerName;

    public AdoptionInputData(String catName, String ownerName) {
        this.catName = catName;
        this.ownerName = ownerName;
    }

    /**
     * Returns the cat's name
     *
     * @return cat's name
     */
    String getCatName() {
        return catName;
    }

    /**
     * Return the owner's name
     *
     * @return owner's name
     */
    String getOwnerName() {
        return ownerName;
    }
}
