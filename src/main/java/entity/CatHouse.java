package entity;

import java.util.Collection;

/**
 * Represents a collection of cats owned by a user.
 * Ensures that each cat has a unique name within the collection.
 */
public class CatHouse {
    private final String ownerId;
    private final Collection<Cat> cats;

    /**
     * Creates a new CatHouse for a specific owner.
     *
     * @param ownerId the ID of the owner
     * @param cats the initial collection of cats
     */
    public CatHouse(String ownerId, Collection<Cat> cats) {
        this.ownerId = ownerId;
        this.cats = cats;
    }

    /**
     * Validates if a cat can be added to this house.
     *
     * @param cat the cat to validate
     * @throws IllegalArgumentException if the cat's name is already taken or if the
     *             cat belongs to a different owner
     */
    public void validateNewCat(Cat cat) {
        if (!cat.getOwnerUsername().equals(ownerId)) {
            throw new IllegalArgumentException("Cat belongs to a different owner");
        }
        if (isNameTaken(cat.getName())) {
            throw new IllegalArgumentException("A cat with this name already exists");
        }
    }

    /**
     * Checks if a cat name is already taken.
     *
     * @param name the name to check
     * @return true if the name is already in use, false otherwise
     */
    public boolean isNameTaken(String name) {
        return cats.stream().anyMatch(cat -> cat.getName().equals(name));
    }

    /**
     * Gets all cats in the house.
     *
     * @return an unmodifiable collection of all cats
     */
    public Collection<Cat> getAllCats() {
        return cats;
    }

    /**
     * Gets the owner ID of this cat house.
     *
     * @return the owner ID
     */
    public String getOwnerId() {
        return ownerId;
    }
}
