package interface_adapter.maxcatserror;

import use_case.maxcatserror.MaxCatsErrorInputBoundary;

/**
 * Controller for the maximum number of cats reached use case.
 */
public class MaxCatsErrorController {
    private final MaxCatsErrorInputBoundary maxCatsUseCaseInteractor;

    public MaxCatsErrorController(MaxCatsErrorInputBoundary maxCatsUseCaseInteractor) {
        this.maxCatsUseCaseInteractor = maxCatsUseCaseInteractor;
    }

    /**
     * Executes the Max Cats Error Use Case.
     *
     */
    public void execute() {
        maxCatsUseCaseInteractor.execute();
    }

    /**
     * Executes the "switch to Break" Use Case.
     */
    public void switchToBreakView() {
        maxCatsUseCaseInteractor.switchToBreakView();
    }
}
