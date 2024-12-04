package app.builder.usecase;

import app.builder.view.Views;
import app.components.DataAccessComponents;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;

/**
 * Builder for logout use case.
 */
public class LogoutUseCaseBuilder extends AbstractUseCaseBuilder {
    private LogoutController logoutController;

    public LogoutUseCaseBuilder(Views views, DataAccessComponents dataAccess) {
        super(views, dataAccess);
    }

    public LogoutController getLogoutController() {
        return logoutController;
    }

    /**
     * Builds the logout related use case.
     */
    public void build() {
        final LogoutOutputBoundary outputBoundary = new LogoutPresenter(
                getViews().getViewManagerModel(),
                getViews().getAuth().getViewModels().getLoggedInViewModel(),
                getViews().getAuth().getViewModels().getLoginViewModel());

        final LogoutInputBoundary interactor = new LogoutInteractor(
                getDataAccess().getUserDataAccess(),
                outputBoundary);

        logoutController = new LogoutController(interactor);

        // Set the logout controller for views that need it
        getViews().getAuth().getViews().getLoggedInView().setLogoutController(logoutController);
        getViews().getSession().getViews().getStudySessionView().setLogoutController(logoutController);
        getViews().getSession().getViews().getBreakSessionView().setLogoutController(logoutController);
    }
}