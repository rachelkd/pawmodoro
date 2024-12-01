package interface_adapter.initialize_cats;

import use_case.cat_management.initialize_cats.InitializeCatsInputBoundary;
import use_case.cat_management.initialize_cats.InitializeCatsInputData;

/**
 * Controller for the Initialize Cats Use Case.
 */
public class InitializeCatsController {
    private final InitializeCatsInputBoundary initializeCatsInteractor;

    public InitializeCatsController(InitializeCatsInputBoundary initializeCatsInteractor) {
        this.initializeCatsInteractor = initializeCatsInteractor;
    }

    /**
     * Executes the Initialize Cats Use Case.
     * @param ownerUsername cat's owner username
     */
    public void execute(String ownerUsername) {
        final InitializeCatsInputData initializeCatsInputData = new InitializeCatsInputData(ownerUsername);
        initializeCatsInteractor.execute(initializeCatsInputData);
    }
}
