package data_access;

import entity.CatImage;
import entity.exceptions.CatImageFetchException;
import use_case.display_cat_image.DisplayCatImageDataAccessInterface;

/**
 * In memory implementation of the DisplayCatImageDataAccessInterface.
 * This class is used for testing purposes only.
 */
public class InMemoryDisplayCatImageDataAccessObject implements DisplayCatImageDataAccessInterface {
    private CatImage mockCatImage;
    private boolean shouldThrowException;

    public InMemoryDisplayCatImageDataAccessObject() {
        this.mockCatImage = new CatImage("test-id", "http://example.com/cat.jpg");
        this.shouldThrowException = false;
    }

    @Override
    public CatImage fetchRandomCatImage() throws CatImageFetchException {
        if (shouldThrowException) {
            throw new CatImageFetchException("Test exception", new RuntimeException());
        }
        return mockCatImage;
    }

    public void setMockCatImage(CatImage mockCatImage) {
        this.mockCatImage = mockCatImage;
    }

    public void setShouldThrowException(boolean shouldThrowException) {
        this.shouldThrowException = shouldThrowException;
    }
}
