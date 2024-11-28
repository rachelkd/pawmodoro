package use_case.cat_management.initialize_cats;

/**
 * Input Boundary of the Initialize Cat Use Case.
 */
public interface InitializeCatsInputBoundary {

    /**
     * Excutes the initialize cat use case.
     * @param initializeCatsInputData the input data
     */
    void execute(InitializeCatsInputData initializeCatsInputData);
}
