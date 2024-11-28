package interface_adapter.initialize_cats;

import use_case.cat_management.initialize_cats.InitializeCatsInputBoundary;
import use_case.cat_management.initialize_cats.InitializeCatsInputData;
import use_case.cat_management.initialize_cats.InitializeCatsInteractor;

/**
 * Controller for the Initialize Cats Use Case.
 */

public class InitializeCatsController {
    private final InitializeCatsInputBoundary initializeCatsInteractor;

    public InitializeCatsController(InitializeCatsInputBoundary initializeCatsInteractor) {
        this.initializeCatsInteractor = initializeCatsInteractor;
    }

    public void execute(String ownerUsername) {
        final InitializeCatsInputData initializeCatsInputData = new InitializeCatsInputData(ownerUsername);
        initializeCatsInteractor.execute(initializeCatsInputData);
    }
}
