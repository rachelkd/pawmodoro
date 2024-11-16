package use_case.display_cat_image;

/**
 * Input boundary for the cat image use case.
 */
public interface DisplayCatImageInputBoundary {
    /**
     * Executes the cat image use case.
     * 
     * @param inputData The input data for the use case
     */
    void execute(DisplayCatImageInputData inputData);
} 
