package use_case.display_cat_stats;

/**
 * Input boundary for the display cat stats use case.
 */
public interface DisplayCatStatsInputBoundary {
    /**
     * Execute the display cat stats use case.
     *
     * @param displayCatStatsInputData The input data containing username and cat name.
     */
    void execute(DisplayCatStatsInputData displayCatStatsInputData);
}
