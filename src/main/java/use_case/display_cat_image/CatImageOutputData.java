package use_case.cat_image;

/**
 * Output data from the cat image use case.
 */
public class CatImageOutputData {
    private final String imageUrl;

    public CatImageOutputData(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
} 
