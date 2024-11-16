package use_case.display_cat_image;

/**
 * Output boundary for the cat image use case.
 */
public interface DisplayCatImageOutputBoundary {
    /**
     * Called when the use case successfully fetches a cat image.
     * 
     * @param outputData The output data containing the image URL
     */
    void prepareSuccessView(DisplayCatImageOutputData outputData);

    /**
     * Called when the use case fails to fetch a cat image.
     * 
     * @param error The error message
     */
    void prepareFailView(String error);
} 
