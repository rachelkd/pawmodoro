package interface_adapter.display_cat_image;

import use_case.display_cat_image.DisplayCatImageInputBoundary;

/**
 * Controller for the cat image feature.
 */
public class DisplayCatImageController {
    private final DisplayCatImageInputBoundary catImageInteractor;

    public DisplayCatImageController(DisplayCatImageInputBoundary catImageInteractor) {
        this.catImageInteractor = catImageInteractor;
    }

    /**
     * Triggers fetching a new cat image.
     */
    public void fetchNewImage() {
        catImageInteractor.execute();
    }
}
