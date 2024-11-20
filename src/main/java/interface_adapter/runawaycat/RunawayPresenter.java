package interface_adapter.runawaycat;

import interface_adapter.ViewManagerModel;
import use_case.runawaycat.RunawayCatOutputBoundary;

// TODO: @manahillsajid please refactor class name to RunawayCatPresenter
/**
 * The presenter for the runaway cat use case.
 */
public class RunawayPresenter implements RunawayCatOutputBoundary {
    private final RunawayCatViewModel runawayCatViewModel;
    private final ViewManagerModel viewManagerModel;

    public RunawayPresenter(RunawayCatViewModel runawayCatViewModel, ViewManagerModel viewManagerModel) {
        this.runawayCatViewModel = runawayCatViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView() {
        // Go back to previous view
    }

    @Override
    public void switchToBreakView() {
        // return to break view
    }
}
