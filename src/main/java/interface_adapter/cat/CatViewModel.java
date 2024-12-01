package interface_adapter.cat;

import interface_adapter.ViewModel;

/**
 * The View Model for the Cat View.
 */
public class CatViewModel extends ViewModel<CatState> {
    public CatViewModel() {
        super("cat");
        setState(new CatState());
    }
}
