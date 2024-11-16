package interface_adapter.display_cat_image;

/**
 * State object for the cat image view.
 */
public class CatImageState {
    private String imageUrl = "";
    private String errorMessage = "";
    private boolean allowRefresh = true;

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
        return this.allowRefresh;
    }

    public void setRefreshAllowed(boolean newAllowRefresh) {
        this.allowRefresh = newAllowRefresh;
    }
}
