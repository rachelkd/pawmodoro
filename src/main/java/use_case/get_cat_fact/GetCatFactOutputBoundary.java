package use_case.get_cat_fact;

/**
 * Output boundary for the cat fact retrieval use case.
 * Defines methods for handling successful and failed cat fact retrievals.
 */
public interface GetCatFactOutputBoundary {
    /**
     * Prepares the success view with the retrieved cat fact.
     * 
     * @param catFact the output data containing the retrieved cat fact
     */
    void prepareSuccessView(GetCatFactOutputData catFact);

    /**
     * Prepares the failure view with error information.
     * 
     * @param catFact the output data containing the error information
     */
    void prepareFailView(GetCatFactOutputData catFact);
}
