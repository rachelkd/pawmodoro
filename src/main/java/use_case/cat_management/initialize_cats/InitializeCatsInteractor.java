package use_case.cat_management.initialize_cats;

import use_case.cat.CatDataAccessInterface;

/**
 * Initialize Cats Interactor
 */
public class InitializeCatsInteractor implements InitializeCatsInputBoundary {
    private final CatDataAccessInterface catDataAccessObject;
    private InitializeCatsOutputBoundary initializeCatsPresenter;

    public InitializeCatsInteractor(CatDataAccessInterface catDataAccessObject,
                                    InitializeCatsOutputBoundary initializeCatsPresenter) {
        this.catDataAccessObject = catDataAccessObject;
        this.initializeCatsPresenter = initializeCatsPresenter;
    }

    @Override
    public void execute(InitializeCatsInputData initializeCatsInputData) {
        // Map<String, Cat> catMap = catDataAccessObject.getCatsByOwner(initializeCatsInputData.getOwnerUsername());

        final InitializeCatsOutputData initializeCatsOutputData = new InitializeCatsOutputData();
    }
}
