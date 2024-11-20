package interface_adapter.create_cat;

import interface_adapter.ViewManagerModel;
import use_case.cat_management.create_cat.CreateCatOutputBoundary;
import use_case.cat_management.create_cat.CreateCatOutputData;
import view.LoggedInView;
import view.MaxCatsErrorView;

public class CreateCatPresenter implements CreateCatOutputBoundary {
    private MaxCatsErrorView maxCatsErrorView;
    private LoggedInView loggedInView;
    private ViewManagerModel viewManagerModel;

    @Override
    public void prepareSuccessView(CreateCatOutputData createCatOutputData) {
        // TODO: @chiually implement
    }

    @Override
    public void prepareFailView(String errorMessage) {

    }
}
