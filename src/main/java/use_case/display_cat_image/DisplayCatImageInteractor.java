package use_case.display_cat_image;

import entity.CatImage;
import entity.exceptions.CatImageFetchException;

/**
 * Interactor for handling cat image use cases.
 */
public class DisplayCatImageInteractor implements DisplayCatImageInputBoundary {
    private final DisplayCatImageDataAccessInterface catImageDataAccessObject;
    private final DisplayCatImageOutputBoundary displayCatImagePresenter;

    public DisplayCatImageInteractor(
            DisplayCatImageDataAccessInterface catImageDataAccessObject,
            DisplayCatImageOutputBoundary displayCatImagePresenter) {
        this.catImageDataAccessObject = catImageDataAccessObject;
        this.displayCatImagePresenter = displayCatImagePresenter;
    }

    @Override
    public void execute() {
        try {
            final CatImage catImage = catImageDataAccessObject.fetchRandomCatImage();
            if (catImage == null) {
                displayCatImagePresenter.prepareFailView("Failed to fetch cat image: No cat image found");
            }
            else {
                final DisplayCatImageOutputData outputData = new DisplayCatImageOutputData(catImage.getImageId(),
                        catImage.getImageUrl(), false);
                displayCatImagePresenter.prepareSuccessView(outputData);
            }
        }
        catch (CatImageFetchException exception) {
            displayCatImagePresenter.prepareFailView("Failed to fetch cat image: " + exception.getMessage());
        }
    }
}
