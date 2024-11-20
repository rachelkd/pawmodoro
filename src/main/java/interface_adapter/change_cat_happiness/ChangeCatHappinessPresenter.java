package interface_adapter.change_cat_happiness;

import use_case.cat_management.change_cat_happiness.ChangeCatHappinessOutputBoundary;
import use_case.cat_management.change_cat_happiness.ChangeCatHappinessOutputData;
import view.BreakSession;
import view.DisplayCatStatsView;
import view.StudySessionView;

public class ChangeCatHappinessPresenter implements ChangeCatHappinessOutputBoundary {
    private DisplayCatStatsView displayCatStatsView;

    public ChangeCatHappinessPresenter(DisplayCatStatsView displayCatStatsView) {
        this.displayCatStatsView = displayCatStatsView;
    }

    @Override
    public void prepareSuccessView(ChangeCatHappinessOutputData changeCatHappinessOutputData) {
        // TODO: Implement the method @chiually
    }
}
