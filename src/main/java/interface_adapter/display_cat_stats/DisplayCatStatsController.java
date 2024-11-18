package interface_adapter.display_cat_stats;

import use_case.display_cat_stats.DisplayCatStatsInputBoundary;
import use_case.display_cat_stats.DisplayCatStatsInputData;

/**
 * The controller for the Display Cat Stats Use Case.
 */
public class DisplayCatStatsController {

    private final DisplayCatStatsInputBoundary displayCatStatsUseCaseInteractor;

    public DisplayCatStatsController(DisplayCatStatsInputBoundary displayCatStatsUseCaseInteractor) {
        this.displayCatStatsUseCaseInteractor = displayCatStatsUseCaseInteractor;
    }

    /**
     * Executes the Display Cat Stats Use Case.
     * 
     * @param username the username of the cat's owner
     * @param catName the name of the cat
     */
    public void execute(String username, String catName) {
        final DisplayCatStatsInputData displayCatStatsInputData = new DisplayCatStatsInputData(
                username, catName);

        displayCatStatsUseCaseInteractor.execute(displayCatStatsInputData);
    }
}
