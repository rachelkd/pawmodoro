package use_case.cat_management.change_cat_hunger;

import use_case.cat_management.change_cat_happiness.ChangeCatHappinessInputData;

/**
 * Input Boundary of Change Cat Hunger Use Case.
 */
public interface ChangeCatHungerInputBoundary {

    /**
     * Executes Change Cat Happiness Use Case.
     * @param changeCatHappinessInputData the input data
     */
    void execute(ChangeCatHappinessInputData changeCatHappinessInputData);
}
