package entity;

/**
 * Factory for building Cat objects.
 */
public class CatFactory {

    public Cat create(String ownerId, String name) {
        return new Cat(ownerId, name);
    }
}
