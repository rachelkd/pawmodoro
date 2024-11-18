package use_case.adoption;

import entity.Cat;

/**
 * The interface of the DAO for the Adoption Use Case
 */
public interface AdoptionDataAccessInterface {

    /**
     * Checks if the user already has a cat by this name.
     *
     * @param catName the given cat name
     * @return true if user already has a cat with this name
     */
    boolean nameAlreadyExists(String catName);

    /**
     * Returns the name of the cat
     */
    String getCatName(Cat cat);


}
