package interface_adapter.cat;

import entity.Cat;
import interface_adapter.ViewModel;

/**
 * The View Model for the Cat View.
 */
public class CatViewModel extends ViewModel<CatState> {
    public CatViewModel() {
        super("cat");
        // setState(new CatState());

        // TODO: Remove this test code once proper cat creation is implemented
        final Cat testCat = new Cat("Test Cat", "test_owner");
        final CatState initialState = new CatState();
        initialState.setImageFileName(testCat.getImageFileName());
        initialState.setCatName(testCat.getName());
        setState(initialState);
    }
}
