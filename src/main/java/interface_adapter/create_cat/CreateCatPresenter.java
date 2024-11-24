package interface_adapter.create_cat;

import interface_adapter.ViewManagerModel;
import use_case.cat_management.create_cat.CreateCatOutputBoundary;
import use_case.cat_management.create_cat.CreateCatOutputData;
import view.LoggedInView;
import view.MaxCatsErrorView;

/**
 * Create Cat Use Case Presenter.
 */
public class CreateCatPresenter implements CreateCatOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private MaxCatsErrorView maxCatsErrorView;
    private LoggedInView loggedInView;

    public CreateCatPresenter(ViewManagerModel viewManagerModel,
                              MaxCatsErrorView maxCatsErrorView,
                              LoggedInView loggedInView) {
        this.maxCatsErrorView = maxCatsErrorView;
        this.loggedInView = loggedInView;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(CreateCatOutputData createCatOutputData) {
        // TODO: @chiually implement
    }

    @Override
    public void prepareFailView(String errorMessage) {

    }
}
