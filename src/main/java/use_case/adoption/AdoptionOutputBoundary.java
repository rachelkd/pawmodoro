package use_case.adoption;

/**
 * The output boundary for the Adoption Use Case
 */
public interface AdoptionOutputBoundary {
    /**
     * Prepares the success view for the Adoption Use Case
     *
     * @param outputData the output data
     */
    void prepareSuccessView(AdoptionOutputData outputData);

    /**
     * Prepares the failure view for the Adoption Use Case.
     *
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

}
