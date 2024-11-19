package use_case.display_cat_stats;

/**
 * Output boundary for the display cat stats use case.
 */
public interface DisplayCatStatsOutputBoundary {
    /**
     * Prepare the success view with cat statistics.
     *
     * @param displayCatStatsOutputData The output data containing cat statistics.
     */
    void prepareSuccessView(DisplayCatStatsOutputData displayCatStatsOutputData);

    /**
     * Prepare the fail view with an error message.
     *
     * @param error The error message to display.
     */
    void prepareFailView(String error);
}
