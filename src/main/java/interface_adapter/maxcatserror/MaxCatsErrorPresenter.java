package interface_adapter.maxcatserror;

import interface_adapter.ViewManagerModel;
import interface_adapter.setupsession.SetupSessionState;
import interface_adapter.setupsession.SetupSessionViewModel;
import use_case.maxcatserror.MaxCatsErrorOutputBoundary;

/**
 * The presenter for the maximum cats reached error use case.
 */
public class MaxCatsErrorPresenter implements MaxCatsErrorOutputBoundary {
    private final MaxCatsErrorViewModel maxCatsViewModel;
    private final ViewManagerModel viewManagerModel;
    //need to go back to break view

    public MaxCatsErrorPresenter(ViewManagerModel viewManagerModel,
            MaxCatsErrorViewModel maxCatsErrorViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.maxCatsViewModel = maxCatsErrorViewModel;
    }

    @Override
    public void prepareSuccessView() {
        //On success, switch to break session view.
    }

    @Override
    public void switchToBreakView() {
        //see above
    }
}
