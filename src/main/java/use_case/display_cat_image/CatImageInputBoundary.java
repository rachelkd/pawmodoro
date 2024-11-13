package use_case.cat_image;

/**
 * Input boundary for the cat image use case.
 */
public interface CatImageInputBoundary {
    /**
     * Executes the cat image use case.
     * 
     * @param inputData The input data for the use case
     */
    void execute(CatImageInputData inputData);
} 
