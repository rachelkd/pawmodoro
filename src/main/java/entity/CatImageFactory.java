package entity;

/**
 * Factory class for creating CatImage objects.
 */
public class CatImageFactory {

    private static final String DEFAULT_IMAGE_ID = "default-id";
    private static final String DEFAULT_IMAGE_URL = "https://example.com/default-cat.jpg";

    /**
     * Creates a new CatImage instance with default values.
     *
     * @return A new CatImage instance with default values.
     */
    public CatImage create() {
        return new CatImage(DEFAULT_IMAGE_ID, DEFAULT_IMAGE_URL);
    }

    /**
     * Creates a new CatImage instance.
     *
     * @param imageId The ID of the cat image.
     * @param imageUrl The URL of the cat image.
     * @return A new CatImage instance.
     */
    public CatImage create(String imageId, String imageUrl) {
        return new CatImage(imageId, imageUrl);
    }
}
