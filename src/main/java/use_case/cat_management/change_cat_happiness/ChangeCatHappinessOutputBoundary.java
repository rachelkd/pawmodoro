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

}
