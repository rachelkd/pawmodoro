package entity;

/**
 * Represents a cat image from The Cat API.
 */
public class CatImage {
    private final String imageUrl;
    private final String imageId;

    public CatImage(String imageId, String imageUrl) {
        this.imageId = imageId;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getImageId() {
        return imageId;
    }
}
