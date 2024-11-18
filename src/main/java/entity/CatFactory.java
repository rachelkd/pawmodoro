package entity;

/**
 * Factory for creating Cat entities.
 */
public class CatFactory {

    /**
     * Creates a new Cat with the given name and owner username.
     *
     * @param name The name for the cat
     * @param ownerUsername The username of the cat's owner
     * @return A new Cat instance
     */
    public Cat create(String name, String ownerUsername) {
        return new Cat(name, ownerUsername);
    }
}
