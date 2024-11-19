package use_case.cat_management.create_cat;

/**
 * Input Boundary of Create Cat Use Case.
 */
public interface CreateCatInputBoundary {

    /**
     * Executes Create Cat Use Case.
     * @param createCatInputData the input data required for use case
     */
    void execute(CreateCatInputData createCatInputData);
}
