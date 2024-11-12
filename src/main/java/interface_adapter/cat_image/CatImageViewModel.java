package interface_adapter.cat_image;

import interface_adapter.ViewModel;

/**
 * View model for displaying cat images.
 */
public class CatImageViewModel extends ViewModel<CatImageState> {

    /**
     * Constructs a new CatImageViewModel.
     * 
     * @param allowRefresh whether the user can request new images
     */
    public CatImageViewModel(boolean allowRefresh) {
        super("cat image");
        setState(new CatImageState(allowRefresh));
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
}
