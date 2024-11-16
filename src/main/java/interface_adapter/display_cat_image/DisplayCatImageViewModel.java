package interface_adapter.display_cat_image;

import interface_adapter.ViewModel;

/**
 * View model for displaying cat images.
 */
public class DisplayCatImageViewModel extends ViewModel<CatImageState> {

    public DisplayCatImageViewModel() {
        super("cat image");
        setState(new CatImageState());
    }

    public String getImageUrl() {
        return getState().getImageUrl();
    }

    /**
     * Sets the URL of the image to display.
     * 
     * @param imageUrl the URL of the image
     */
    public void setImageUrl(String imageUrl) {
        final CatImageState state = getState();
        state.setImageUrl(imageUrl);
        setState(state);
    }

    public String getErrorMessage() {
        return getState().getErrorMessage();

    }

    /**
     * Sets the error message to display.
     * 
     * @param errorMessage the error message
     */
    public void setErrorMessage(String errorMessage) {
        final CatImageState state = getState();
        state.setErrorMessage(errorMessage);
        setState(state);
    }

    /**
     * Checks if refreshing images is allowed.
     * 
     * @return true if refresh is allowed, false otherwise
     */
    public boolean isRefreshAllowed() {
        return getState().isRefreshAllowed();
    }

    /**
     * Sets whether refreshing images is allowed.
     *
     * @param newAllowRefresh true if refresh is allowed, false otherwise
     */
    public void setRefreshAllowed(boolean newAllowRefresh) {
        final CatImageState state = getState();
        state.setRefreshAllowed(newAllowRefresh);
        setState(state);
    }
}
