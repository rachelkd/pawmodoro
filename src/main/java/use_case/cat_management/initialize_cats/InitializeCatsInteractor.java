package use_case.cat_management.initialize_cats;

import java.util.Collection;

import entity.Cat;
import use_case.cat.CatDataAccessInterface;

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
        final Collection<Cat> cats = catDataAccessObject.getCatsByOwner(initializeCatsInputData.getOwnerUsername());

        final InitializeCatsOutputData initializeCatsOutputData = new InitializeCatsOutputData(cats);
        initializeCatsPresenter.prepareSuccessView(initializeCatsOutputData);
    }
}
