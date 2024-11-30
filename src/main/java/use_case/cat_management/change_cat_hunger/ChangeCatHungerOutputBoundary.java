package use_case.cat_management.change_cat_hunger;

/**
 * Interface for the output boundary of the Change Cat Hunger Use Case.
 */
public interface ChangeCatHungerOutputBoundary {

    /**
     * Prepares updates to Break and Study Session Views.
     * @param changeCatHungerOutputData output data
     */
    void prepareSuccessView(ChangeCatHungerOutputData changeCatHungerOutputData);

    /**
     * Sends error message to the Inventory View.
     * @param errorMessage the error message
     */
    void prepareFailView(String errorMessage);
}
