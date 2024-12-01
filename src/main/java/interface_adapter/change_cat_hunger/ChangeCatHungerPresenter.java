package interface_adapter.change_cat_hunger;

import interface_adapter.ViewManagerModel;
import interface_adapter.cat.CatState;
import interface_adapter.display_cat_stats.DisplayCatStatsViewModel;
import use_case.cat_management.change_cat_hunger.ChangeCatHungerOutputBoundary;
import use_case.cat_management.change_cat_hunger.ChangeCatHungerOutputData;

/**
 * Change Cat Hunger Use Case Presenter.
 */
public class ChangeCatHungerPresenter implements ChangeCatHungerOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private DisplayCatStatsViewModel displayCatStatsViewModel;

    public ChangeCatHungerPresenter(ViewManagerModel viewManagerModel,
                                    DisplayCatStatsViewModel displayCatStatsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.displayCatStatsViewModel = displayCatStatsViewModel;
    }

    @Override
    public void prepareSuccessView(ChangeCatHungerOutputData changeCatHungerOutputData) {
        final CatState catState = displayCatStatsViewModel.getState();
        catState.setHungerLevel(changeCatHungerOutputData.getNewHungerLevel());

        displayCatStatsViewModel.setState(catState);
        displayCatStatsViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        displayCatStatsViewModel.getState().setError(errorMessage);
        displayCatStatsViewModel.firePropertyChanged("null_cat_name");
    }
}
