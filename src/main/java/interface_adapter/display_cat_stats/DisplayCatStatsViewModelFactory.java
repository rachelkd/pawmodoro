package interface_adapter.display_cat_stats;

import interface_adapter.cat.CatState;

public class DisplayCatStatsViewModelFactory {

    public DisplayCatStatsViewModel createDisplayCatStatsViewModel(CatState catState) {
        final DisplayCatStatsViewModel displayCatStatsViewModel = new DisplayCatStatsViewModel();
        displayCatStatsViewModel.setState(catState);
        return displayCatStatsViewModel;
    }
}
