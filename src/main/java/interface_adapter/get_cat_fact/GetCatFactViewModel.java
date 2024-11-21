package interface_adapter.get_cat_fact;

import interface_adapter.ViewModel;

/**
 * View model for displaying cat facts.
 */
public class GetCatFactViewModel extends ViewModel<GetCatFactState> {
    public GetCatFactViewModel() {
        super("get cat fact");
        setState(new GetCatFactState());
    }
}
