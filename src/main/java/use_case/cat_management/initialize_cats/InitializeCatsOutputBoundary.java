package use_case.cat_management.initialize_cats;

/**
 * The output boundary for the initialize cat use case.
 */
public interface InitializeCatsOutputBoundary {

    /**
     * Prepare the success view when cats are successful initialized.
     * @param initializeCatsOutputData the output data
     */
    void prepareSuccessView(InitializeCatsOutputData initializeCatsOutputData);
}
