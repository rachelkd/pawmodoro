package interface_adapter.display_cat_stats;

import interface_adapter.cat.CatState;
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
        final CatState catState = new CatState();
        catState.setCatName(displayCatStatsOutputData.getCatName());
        catState.setHungerLevel(displayCatStatsOutputData.getHungerLevel());
        catState.setHappinessLevel(displayCatStatsOutputData.getHappinessLevel());
        catState.setImageFileName(displayCatStatsOutputData.getImageFileName());

        this.displayCatStatsViewModel.setState(catState);
        this.displayCatStatsViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final CatState catState = new CatState();
        catState.setError(error);

        this.displayCatStatsViewModel.setState(catState);
        this.displayCatStatsViewModel.firePropertyChanged();
    }
}
