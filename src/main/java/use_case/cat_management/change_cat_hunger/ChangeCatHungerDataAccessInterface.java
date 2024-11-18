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
    boolean saveCat(Cat cat);

    /**
     * Retrieves all cats owned by a specific user.
     *
     * @param ownerUsername the ID of the owner whose cats to retrieve
     * @return a collection of cats owned by the user
     * @throws IllegalArgumentException if ownerId is null or empty
     */
    Collection<Cat> getCatsByOwner(String ownerUsername);

    /**
     * Checks if a cat with the given name already exists for the specified owner.
     *
     * @param name the name to check
     * @param ownerUsername the ID of the owner
     * @return true if a cat with the given name exists for the owner, false
     *         otherwise
     * @throws IllegalArgumentException if name or ownerId is null or empty
     */
    boolean existsByNameAndOwner(String name, String ownerUsername);

    /**
     * Get the hunger level of a cat.
     *
     * @param name the name of the cat
     * @param ownerUsername the username of the cat's owner
     * @return the hunger level of the cat
     * @throws entity.exceptions.NoCatsFoundException if the cat is not found
     */
    int getHungerLevel(String name, String ownerUsername);

    boolean updateCat(Cat cat);
}
