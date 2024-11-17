package use_case.adoption;

import entity.User;

/**
 * The Input Data for the Adoption Use Case
 */
public class AdoptionInputData {
    private final String catName;
    private final User user;

    public AdoptionInputData(String catName, User user) {
        this.catName = catName;
        this.user = user;
    }

    /**
     * Returns the cat's name
     *
     * @return cat's name
     */
    String getCatName() {
        return catName;
    }

}
