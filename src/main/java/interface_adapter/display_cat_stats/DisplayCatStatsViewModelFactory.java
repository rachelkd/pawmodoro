package interface_adapter.display_cat_stats;

import interface_adapter.cat.CatState;

/**
 * Factory for creating cat DisplayCaStatViewModels.
 */
public class DisplayCatStatsViewModelFactory {

    /**
     * Creates a DisplayCatStatsViewModel for a cat state.
     * @param catState the state of the cat
     * @return the DisplayCatStatsViewModel
     */
    public DisplayCatStatsViewModel createDisplayCatStatsViewModel(CatState catState) {
        final DisplayCatStatsViewModel displayCatStatsViewModel = new DisplayCatStatsViewModel();
        displayCatStatsViewModel.setState(catState);
        return displayCatStatsViewModel;
    }
}
