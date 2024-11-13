package use_case.display_cat_image;

import entity.CatImage;

/**
 * Interactor for handling cat image use cases.
 */
public class CatImageInteractor implements CatImageInputBoundary {
    private final CatImageDataAccessInterface catImageDataAccessObject;
    private final CatImageOutputBoundary catImagePresenter;

    public CatImageInteractor(CatImageDataAccessInterface catImageDataAccessObject,
            CatImageOutputBoundary catImagePresenter) {
        this.catImageDataAccessObject = catImageDataAccessObject;
        this.catImagePresenter = catImagePresenter;
    }

    @Override
    public void execute(CatImageInputData inputData) {
        try {
            final CatImage catImage = catImageDataAccessObject.fetchRandomCatImage();
            final CatImageOutputData outputData = new CatImageOutputData(catImage.getImageUrl());
            catImagePresenter.prepareSuccessView(outputData);
        }
        catch (Exception e) {
            catImagePresenter.prepareFailView("Failed to fetch cat image: " + e.getMessage());
        }
    }
}
