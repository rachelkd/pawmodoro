package interface_adapter.cat_image;

/**
 * State object for the cat image view.
 */
public class CatImageState {
    private String imageUrl = "";
    private String errorMessage = "";
    private final boolean allowRefresh;

    /**
     * Creates a new CatImageState.
     * 
     * @param allowRefresh whether the user can request new images
     */
    public CatImageState(boolean allowRefresh) {
        this.allowRefresh = allowRefresh;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isRefreshAllowed() {
        return allowRefresh;
    }
}
