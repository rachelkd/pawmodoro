package use_case.display_cat_image;

import entity.CatImage;
import entity.exceptions.CatImageFetchException;

/**
 * Interface for accessing cat images from an external source.
 */
public interface DisplayCatImageDataAccessInterface {
    /**
     * Fetches a random cat image from the data source.
     * 
     * @return A CatImage entity containing the image data
     * @throws CatImageFetchException if there's an error fetching the image
     */
    CatImage fetchRandomCatImage() throws CatImageFetchException;
}
