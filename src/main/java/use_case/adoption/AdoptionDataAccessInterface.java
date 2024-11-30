package use_case.adoption;

import entity.Cat;

/**
 * The interface of the DAO for the Adoption Use Case
 */
public interface AdoptionDataAccessInterface {

    /**
     * Returns the name of the owner
     */
    String getCurrentUsername();
}
