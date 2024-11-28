package interface_adapter.initialize_cats;

import interface_adapter.ViewModel;

public class InitializeCatsViewModel extends ViewModel<InitializeCatsState> {

    public InitializeCatsViewModel() {
        super("cat_container");
        setState(new InitializeCatsState());
    }
}
