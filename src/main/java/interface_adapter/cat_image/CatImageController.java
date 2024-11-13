package interface_adapter.cat_image;

import use_case.display_cat_image.CatImageInputBoundary;
import use_case.display_cat_image.CatImageInputData;

/**
 * Controller for the cat image feature.
 */
public class CatImageController {
    private final CatImageInputBoundary catImageInteractor;

    public CatImageController(CatImageInputBoundary catImageInteractor) {
        this.catImageInteractor = catImageInteractor;
    }

    /**
     * Triggers fetching a new cat image.
     */
    public void fetchNewImage() {
        final CatImageInputData inputData = new CatImageInputData();
        catImageInteractor.execute(inputData);
    }
}
