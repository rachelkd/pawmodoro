package interface_adapter.get_cat_fact;

import use_case.get_cat_fact.GetCatFactOutputBoundary;
import use_case.get_cat_fact.GetCatFactOutputData;

/**
 * Presenter for the cat fact feature.
 */
public class GetCatFactPresenter implements GetCatFactOutputBoundary {
    private final GetCatFactViewModel getCatFactViewModel;

    public GetCatFactPresenter(GetCatFactViewModel getCatFactViewModel) {
        this.getCatFactViewModel = getCatFactViewModel;
    }

    @Override
    public void prepareSuccessView(GetCatFactOutputData catFactData) {
        final GetCatFactState state = getCatFactViewModel.getState();
        state.setCatFact(catFactData.getCatFact());
        state.setError(null);
        getCatFactViewModel.setState(state);
        getCatFactViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(GetCatFactOutputData catFactData) {
        final GetCatFactState state = getCatFactViewModel.getState();
        state.setCatFact("");
        state.setError(catFactData.getErrorMessage());
        getCatFactViewModel.setState(state);
        getCatFactViewModel.firePropertyChanged();
    }
}
