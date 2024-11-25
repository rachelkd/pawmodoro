package interface_adapter.get_cat_fact;

import use_case.get_cat_fact.GetCatFactInputBoundary;

/**
 * Controller for the cat fact feature.
 */
public class GetCatFactController {
    private final GetCatFactInputBoundary getCatFactInteractor;

    public GetCatFactController(GetCatFactInputBoundary getCatFactInteractor) {
        this.getCatFactInteractor = getCatFactInteractor;
    }

    /**
     * Executes the cat fact retrieval use case.
     */
    public void execute() {
        getCatFactInteractor.execute();
    }
}
