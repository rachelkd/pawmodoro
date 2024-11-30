package interface_adapter.change_cat_happiness;

import interface_adapter.ViewManagerModel;
import interface_adapter.cat.CatState;
import interface_adapter.display_cat_stats.DisplayCatStatsViewModel;
import interface_adapter.runawaycat.RunawayCatState;
import interface_adapter.runawaycat.RunawayCatViewModel;
import interface_adapter.study_session.StudySessionViewModel;
import use_case.cat_management.change_cat_happiness.ChangeCatHappinessOutputBoundary;
import use_case.cat_management.change_cat_happiness.ChangeCatHappinessOutputData;

/**
 * Change Cat Happiness Use Case Presenter.
 */
public class ChangeCatHappinessPresenter implements ChangeCatHappinessOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final StudySessionViewModel studySessionViewModel;
    private final DisplayCatStatsViewModel displayCatStatsViewModel;
    private final RunawayCatViewModel runawayCatViewModel;

    public ChangeCatHappinessPresenter(ViewManagerModel viewManagerModel,
                                       StudySessionViewModel studySessionViewModel,
                                       DisplayCatStatsViewModel displayCatStatsViewModel,
                                       RunawayCatViewModel runawayCatView) {
        this.viewManagerModel = viewManagerModel;
        this.studySessionViewModel = studySessionViewModel;
        this.displayCatStatsViewModel = displayCatStatsViewModel;
        this.runawayCatViewModel = runawayCatView;
    }

    @Override
    public void prepareSuccessView(ChangeCatHappinessOutputData changeCatHappinessOutputData) {
        final CatState catState = displayCatStatsViewModel.getState();
        catState.setHappinessLevel(changeCatHappinessOutputData.getNewHappinessLevel());

        displayCatStatsViewModel.setState(catState);
        displayCatStatsViewModel.firePropertyChanged();
    }

    @Override
    public void switchToRunawayCatView(String catName, String ownerUsername) {
        final RunawayCatState runawayCatState = runawayCatViewModel.getState();
        runawayCatState.setCatName(catName);
        runawayCatViewModel.setState(runawayCatState);

        viewManagerModel.setState(runawayCatViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailureView(String errorMessage) {
        studySessionViewModel.getState().setCatError(errorMessage);
        studySessionViewModel.firePropertyChanged("null_cat_name");
    }
}
