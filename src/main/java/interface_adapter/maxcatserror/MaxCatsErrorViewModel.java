package interface_adapter.maxcatserror;

import interface_adapter.ViewModel;
import interface_adapter.signup.SignupState;

/**
 * The view model for the maximum cats reached error use case.
 */
public class MaxCatsErrorViewModel extends ViewModel<MaxCatsErrorState> {
    public static final String ERROR_MESSAGE = "Oh no! You have reached the maximum number of cats! You cannot adopt at the moment";
    public static final String RETURN_BUTTON_LABEL = "Return";

    public MaxCatsErrorViewModel() {
        super("maximum cats error");
        setState(new MaxCatsErrorState());
    }

}
