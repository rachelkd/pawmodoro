package interface_adapter.change_cat_hunger;

import use_case.cat_management.change_cat_hunger.ChangeCatHungerOutputBoundary;
import use_case.cat_management.change_cat_hunger.ChangeCatHungerOutputData;
import view.DisplayCatStatsView;

public class ChangeCatHungerPresenter implements ChangeCatHungerOutputBoundary {
    private DisplayCatStatsView displayCatStatsView;

    public ChangeCatHungerPresenter(DisplayCatStatsView displayCatStatsView) {
        this.displayCatStatsView = displayCatStatsView;
    }

    @Override
    public void prepareSuccessView(ChangeCatHungerOutputData changeCatHungerOutputData) {
        // TODO: @chiually implement
    }
}
