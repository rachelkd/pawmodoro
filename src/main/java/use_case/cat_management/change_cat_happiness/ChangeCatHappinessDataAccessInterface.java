package use_case.cat_management.change_cat_happiness;

import java.util.Collection;

import entity.Cat;

/**
 * Repository interface for managing cat persistence.
 * This interface defines the contract for storing and retrieving cats
 * associated with users.
 */
public interface ChangeCatHappinessDataAccessInterface {

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
     * Update happiness level of cat in database
     *
     * @param ownerId the owner id
     * @param catName the cat's name
     * @param newHappinessLevel the integer representing happiness from 0 to 100
     */
    void updateHappinessLevel(String ownerId, String catName, int newHappinessLevel);

    Cat getCatByNameAndOwnerId(String catName, String ownerId);
}
