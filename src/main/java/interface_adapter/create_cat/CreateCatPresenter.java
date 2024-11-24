package interface_adapter.create_cat;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.maxcatserror.MaxCatsErrorViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.cat_management.create_cat.CreateCatOutputBoundary;
import use_case.cat_management.create_cat.CreateCatOutputData;

/**
 * Create Cat Use Case Presenter.
 */
public class CreateCatPresenter implements CreateCatOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private MaxCatsErrorViewModel maxCatsErrorViewModel;
    private LoginViewModel loginViewModel;
    private SignupViewModel signupViewModel;

    public CreateCatPresenter(ViewManagerModel viewManagerModel,
                              MaxCatsErrorViewModel maxCatsErrorView,
                              LoginViewModel loginViewModel,
                              SignupViewModel signupViewModel) {
        this.maxCatsErrorViewModel = maxCatsErrorView;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;
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