package use_case.get_cat_fact;

import entity.exceptions.CatFactRetrievalException;

/**
 * Interactor responsible for retrieving cat facts from the data access layer
 * and preparing them for presentation.
 */
public class GetCatFactInteractor implements GetCatFactInputBoundary {
    private final CatFactDataAccessInterface catFactDataAccessObject;
    private final GetCatFactOutputBoundary getCatFactPresenter;

    public GetCatFactInteractor(CatFactDataAccessInterface catFactDataAccessObject,
            GetCatFactOutputBoundary getCatFactPresenter) {
        this.catFactDataAccessObject = catFactDataAccessObject;
        this.getCatFactPresenter = getCatFactPresenter;
    }

    @Override
    public void execute() {
        try {
            final String catFact = catFactDataAccessObject.getCatFact();
            final GetCatFactOutputData outputData = new GetCatFactOutputData(catFact, false, null);
            getCatFactPresenter.prepareSuccessView(outputData);
        }
        catch (CatFactRetrievalException exception) {
            final GetCatFactOutputData outputData = new GetCatFactOutputData(null, true, exception.getMessage());
            getCatFactPresenter.prepareFailView(outputData);
        }
    }
}
