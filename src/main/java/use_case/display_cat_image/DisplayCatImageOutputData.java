package use_case.display_cat_image;

/**
 * Output data from the cat image use case.
 */
public class DisplayCatImageOutputData {
    private final String imageUrl;
    private final boolean useCaseFailed;
    private final String imageId;

    public DisplayCatImageOutputData(String imageId, String imageUrl, boolean useCaseFailed) {
        this.imageId = imageId;
        this.imageUrl = imageUrl;
        this.useCaseFailed = useCaseFailed;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getImageId() {
        return imageId;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
} 
