package use_case.cat_image;

import entity.CatImage;

/**
 * Interface for accessing cat images from an external source.
 */
public interface CatImageDataAccessInterface {
    /**
     * Fetches a random cat image from the data source.
     * 
     * @return A CatImage entity containing the image data
     * @throws Exception if there's an error fetching the image
     */
    CatImage fetchRandomCatImage() throws Exception;
} 
