package interface_adapter.adoption;

import interface_adapter.ViewModel;

/**
 * The View Model for the adoption use case.
 */
public class AdoptionViewModel extends ViewModel<AdoptionState> {
    public static final String TITLE_LABEL = "Adoption";
    public static final String NAME_LABEL = "Enter your new cats' name:";
    public static final String RETURN_LABEL = "Return";
    public static final String ADOPTION_DONE_LABEL = "Congratulations! You have adopted a new cat!";

    public static final String CONFIRM_BUTTON_LABEL = "Adopt now!";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    public AdoptionViewModel() {
        super("adoption");
        setState(new AdoptionState());
    }

}
