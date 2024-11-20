package app.builder.usecase;

import app.builder.view.Views;
import app.components.DataAccessComponents;
import entity.CommonUserFactory;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordPresenter;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;

/**
 * Builder for authentication-related use cases.
 */
public class AuthUseCaseBuilder extends AbstractUseCaseBuilder {
    private final CommonUserFactory userFactory;

    public AuthUseCaseBuilder(Views views, DataAccessComponents dataAccess) {
        super(views, dataAccess);
        this.userFactory = new CommonUserFactory();
    }

    /**
     * Builds the login use case.
     *
     * @return this builder for method chaining
     */
    public AuthUseCaseBuilder buildLoginUseCase() {
        final LoginOutputBoundary outputBoundary = new LoginPresenter(
                getViews().getViewManagerModel(),
                getViews().getSession().getViewModels().getStudySessionViewModel(),
                getViews().getAuth().getViewModels().getLoginViewModel());

        final LoginInputBoundary interactor = new LoginInteractor(
                getDataAccess().getUserDataAccess(),
                outputBoundary);

        final LoginController controller = new LoginController(interactor);
        getViews().getAuth().getViews().getLoginView().setLoginController(controller);
        return this;
    }

    /**
     * Builds the signup use case.
     *
     * @return this builder for method chaining
     */
    public AuthUseCaseBuilder buildSignupUseCase() {
        final SignupOutputBoundary outputBoundary = new SignupPresenter(
                getViews().getViewManagerModel(),
                getViews().getAuth().getViewModels().getSignupViewModel(),
                getViews().getAuth().getViewModels().getLoginViewModel());

        final SignupInputBoundary interactor = new SignupInteractor(
                getDataAccess().getUserDataAccess(),
                outputBoundary,
                userFactory);

        final SignupController controller = new SignupController(interactor);
        getViews().getAuth().getViews().getSignupView().setSignupController(controller);
        return this;
    }

    /**
     * Builds the logout use case.
     *
     * @return this builder for method chaining
     */
    public AuthUseCaseBuilder buildLogoutUseCase() {
        final LogoutOutputBoundary outputBoundary = new LogoutPresenter(
                getViews().getViewManagerModel(),
                getViews().getAuth().getViewModels().getLoggedInViewModel(),
                getViews().getAuth().getViewModels().getLoginViewModel());

        final LogoutInputBoundary interactor = new LogoutInteractor(
                getDataAccess().getUserDataAccess(),
                outputBoundary);

        final LogoutController controller = new LogoutController(interactor);
        getViews().getAuth().getViews().getLoggedInView().setLogoutController(controller);
        return this;
    }

    /**
     * Builds the change password use case.
     *
     * @return this builder for method chaining
     */
    public AuthUseCaseBuilder buildChangePasswordUseCase() {
        final ChangePasswordOutputBoundary outputBoundary = new ChangePasswordPresenter(
                getViews().getAuth().getViewModels().getLoggedInViewModel());

        final ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(
                getDataAccess().getUserDataAccess(),
                outputBoundary,
                userFactory);

        final ChangePasswordController controller = new ChangePasswordController(interactor);
        getViews().getAuth().getViews().getLoggedInView().setChangePasswordController(controller);
        return this;
    }

    /**
     * Builds all authentication use cases.
     *
     * @return this builder for method chaining
     */
    public AuthUseCaseBuilder build() {
        return this
                .buildLoginUseCase()
                .buildSignupUseCase()
                .buildLogoutUseCase()
                .buildChangePasswordUseCase();
    }
}
