package use_case.adoption;

/**
 * The interface of the DAO for the Adoption Use Case.
 */
public interface AdoptionDataAccessInterface {

    /**
     * Returns the name of the owner.
     * @return the owner's name
     */
    String getCurrentUsername();
}
