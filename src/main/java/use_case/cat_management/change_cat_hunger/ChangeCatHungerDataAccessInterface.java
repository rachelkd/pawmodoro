package use_case.cat_management.change_cat_hunger;

import entity.Cat;

import java.util.Collection;

/**
 * Repository interface for managing cat persistence.
 * This interface defines the contract for storing and retrieving cats
 * associated with users.
 */
public interface ChangeCatHungerDataAccessInterface {

    /**
     * Saves a cat to the repository.
     * If the cat already exists (same name and owner), it will be updated.
     *
     * @param cat the cat to save
     * @throws IllegalArgumentException if cat is null
     */
    void save(Cat cat);

    /**
     * Retrieves all cats owned by a specific user.
     *
     * @param ownerId the ID of the owner whose cats to retrieve
     * @return a collection of cats owned by the user
     * @throws IllegalArgumentException if ownerId is null or empty
     */
    Collection<Cat> findByOwnerId(String ownerId);

    /**
     * Checks if a cat with the given name already exists for the specified owner.
     *
     * @param name the name to check
     * @param ownerId the ID of the owner
     * @return true if a cat with the given name exists for the owner, false
     *         otherwise
     * @throws IllegalArgumentException if name or ownerId is null or empty
     */
    boolean existsByNameAndOwnerId(String name, String ownerId);

    /**
     * Updates the hunger level of the cat in database
     *
     * @param ownerId the owner id
     * @param catName the name of the cat
     * @param newHungerLevel the new hunger level of the cat from 0 to 100
     */
    void updateHungerLevels(String ownerId, String catName, int newHungerLevel);
}
