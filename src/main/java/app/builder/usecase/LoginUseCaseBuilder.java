package app.builder.usecase;

import app.builder.view.Views;
import app.components.DataAccessComponents;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;

/**
 * Builder for login use case.
 */
public class LoginUseCaseBuilder extends AbstractUseCaseBuilder {
    
    public LoginUseCaseBuilder(Views views, DataAccessComponents dataAccess) {
        super(views, dataAccess);
    }

    /**
     * Builds the login related use case.
     */
    public void build() {
        final LoginOutputBoundary outputBoundary = new LoginPresenter(
                getViews().getViewManagerModel(),
                getViews().getSession().getViewModels().getStudySessionViewModel(),
                getViews().getAuth().getViewModels().getLoginViewModel(),
                getViews().getCat().getViewModels().getRunawayCatViewModel(),
                getViews().getCat().getViewModels().getAdoptionViewModel());

        final LoginInputBoundary interactor = new LoginInteractor(
                getDataAccess().getUserDataAccess(),
                outputBoundary);

        final LoginController controller = new LoginController(interactor);
        getViews().getAuth().getViews().getLoginView().setLoginController(controller);
    }
}
