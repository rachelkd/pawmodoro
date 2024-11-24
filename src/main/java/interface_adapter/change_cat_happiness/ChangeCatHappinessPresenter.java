package interface_adapter.change_cat_happiness;

import interface_adapter.ViewManagerModel;
import interface_adapter.display_cat_stats.DisplayCatStatsViewModel;
import use_case.cat_management.change_cat_happiness.ChangeCatHappinessOutputBoundary;
import use_case.cat_management.change_cat_happiness.ChangeCatHappinessOutputData;

/**
 * Change Cat Happiness Use Case Presenter.
 */
public class ChangeCatHappinessPresenter implements ChangeCatHappinessOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private DisplayCatStatsViewModel displayCatStatsViewModel;

    public ChangeCatHappinessPresenter(ViewManagerModel viewManagerModel,
                                       DisplayCatStatsViewModel displayCatStatsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.displayCatStatsViewModel = displayCatStatsViewModel;
    }

    @Override
    public void prepareSuccessView(ChangeCatHappinessOutputData changeCatHappinessOutputData) {
        // TODO: Implement the method @chiually
    }
}
