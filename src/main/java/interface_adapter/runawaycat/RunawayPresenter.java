package interface_adapter.runawaycat;

import interface_adapter.ViewManagerModel;
import use_case.runawaycat.RunawayCatOutputBoundary;

public class RunawayPresenter implements RunawayCatOutputBoundary {
    private final RunawayCatViewModel runawayCatViewModel;
    private final ViewManagerModel viewManagerModel;

    public RunawayPresenter(RunawayCatViewModel runawayCatViewModel, ViewManagerModel viewManagerModel) {
        this.runawayCatViewModel = runawayCatViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSucessView() {
        //Go back to previous view
    }

    @Override
    public void switchToBreakView() {
        // return to break view
    }
}