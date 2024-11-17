package use_case.cat_management.create_cat;

/**
 * Interface for the output boundary of the Create Cat Use Case.
 */
public interface CreateCatOutputBoundary {

    /**
     * Prepares the success view for the Create Cat Use Case.
     * @param createCatOutputData output data
     */
    void prepareSuccessView(CreateCatOutputData createCatOutputData);

    /**
     * Prepares the fail view for the Create Cat Use Case.
     *
     * @param errorMessage explanation of failure
     */
    void prepareFailView(String errorMessage);
}
