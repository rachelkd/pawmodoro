package interface_adapter.initialize_cats;

import use_case.cat_management.initialize_cats.InitializeCatsInputData;
import use_case.cat_management.initialize_cats.InitializeCatsInteractor;

/**
 * Controller for the Initialize Cats Use Case.
 */
public class InitializeCatsController {
    private final InitializeCatsInteractor initializeCatsInteractor;

    public InitializeCatsController(InitializeCatsInteractor initializeCatsInteractor) {
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