package app.builder.usecase;

import app.builder.view.Views;
import app.components.DataAccessComponents;
import entity.CommonUserFactory;
import interface_adapter.logout.LogoutController;

/**
 * Coordinates the building of authentication-related use cases.
 */
public class AuthUseCaseBuilder extends AbstractUseCaseBuilder {
    private final CommonUserFactory userFactory;
    private final LoginUseCaseBuilder loginBuilder;
    private final SignupUseCaseBuilder signupBuilder;
    private final LogoutUseCaseBuilder logoutBuilder;
    private final ChangePasswordUseCaseBuilder changePasswordBuilder;

    public AuthUseCaseBuilder(Views views, DataAccessComponents dataAccess) {
        super(views, dataAccess);
        this.userFactory = new CommonUserFactory();
        this.loginBuilder = new LoginUseCaseBuilder(views, dataAccess);
        this.signupBuilder = new SignupUseCaseBuilder(views, dataAccess, userFactory);
        this.logoutBuilder = new LogoutUseCaseBuilder(views, dataAccess);
        this.changePasswordBuilder = new ChangePasswordUseCaseBuilder(views, dataAccess, userFactory);
    }

    /**
     * Gets the logout controller.
     * @return the logout controller
     */
    public LogoutController getLogoutController() {
        return logoutBuilder.getLogoutController();
    }

    /**
     * Builds all authentication use cases.
     * @return this builder
     */
    public AuthUseCaseBuilder build() {
        loginBuilder.build();
        signupBuilder.build();
        logoutBuilder.build();
        changePasswordBuilder.build();
        return this;
    }
}
