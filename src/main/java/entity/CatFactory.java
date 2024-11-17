package entity;

/**
 * Factory for creating Cat entities.
 */
public class CatFactory {

    /**
     * Creates a new Cat with the given name and owner ID.
     *
     * @param name The name for the cat
     * @param ownerId The ID of the cat's owner
     * @return A new Cat instance
     */
    public Cat create(String name, String ownerId) {
        return new Cat(name, ownerId);
    }
}
