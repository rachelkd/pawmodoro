package entity;

/**
 * Represents a cat image from The Cat API.
 */
public class CatImage {
    private final String imageUrl;
    private final String id;

    public CatImage(String id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getId() {
        return id;
    }
} 
