package use_case.cat_management.change_cat_happiness;

/**
 * Interface for the output boundary of the Change Cat Happiness Use Case.
 */
public interface ChangeCatHappinessOutputBoundary {

    /**
     * Prepares updates to Break and Study Session Views.
     * @param changeCatHappinessOutputData output data
     */
    void prepareSuccessView(ChangeCatHappinessOutputData changeCatHappinessOutputData);

    /**
     * Switches to RunawayCat view when cat runs away due to low happiness.
     * @param catName the name of the cat running away
     * @param ownerUsername username of cat owner
     */
    void switchToRunawayCatView(String catName, String ownerUsername);

    /**
     * Prepares error message if you did not select a cat.
     * @param errorMessage the error message
     */
    void prepareFailureView(String errorMessage);
}
