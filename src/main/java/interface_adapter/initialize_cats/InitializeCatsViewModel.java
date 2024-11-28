package interface_adapter.initialize_cats;

import interface_adapter.ViewModel;

public class InitializeCatsViewModel extends ViewModel<InitializeCatsState> {

    public InitializeCatsViewModel() {
        super("initialize_cats");
        setState(new InitializeCatsState());
    }
}
