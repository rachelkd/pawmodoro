package interface_adapter.display_cat_stats;

import use_case.display_cat_stats.DisplayCatStatsOutputBoundary;
import use_case.display_cat_stats.DisplayCatStatsOutputData;

/**
 * Presenter for the display cat stats use case.
 */
public class DisplayCatStatsPresenter implements DisplayCatStatsOutputBoundary {
    private final DisplayCatStatsViewModel displayCatStatsViewModel;

    public DisplayCatStatsPresenter(DisplayCatStatsViewModel displayCatStatsViewModel) {
        this.displayCatStatsViewModel = displayCatStatsViewModel;
    }

    @Override
    public void prepareSuccessView(DisplayCatStatsOutputData displayCatStatsOutputData) {
        final DisplayCatStatsState displayCatStatsState = new DisplayCatStatsState();
        displayCatStatsState.setCatName(displayCatStatsOutputData.getCatName());
        displayCatStatsState.setHungerLevel(displayCatStatsOutputData.getHungerLevel());
        displayCatStatsState.setHappinessLevel(displayCatStatsOutputData.getHappinessLevel());
        displayCatStatsState.setImageFileName(displayCatStatsOutputData.getImageFileName());

        this.displayCatStatsViewModel.setState(displayCatStatsState);
        this.displayCatStatsViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final DisplayCatStatsState displayCatStatsState = new DisplayCatStatsState();
        displayCatStatsState.setError(error);

        this.displayCatStatsViewModel.setState(displayCatStatsState);
        this.displayCatStatsViewModel.firePropertyChanged();
    }
}
