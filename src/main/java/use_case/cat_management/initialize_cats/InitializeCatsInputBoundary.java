package use_case.cat_management.initialize_cats;

/**
 * Input Boundary of the Initialize Cat Use Case.
 */
public interface InitializeCatsInputBoundary {

    /**
     * Executes the initialize cats use case.
     * @param initializeCatsInputData the input data
     */
    void execute(InitializeCatsInputData initializeCatsInputData);
}
