package app.builder.usecase;

import app.builder.view.Views;
import app.components.DataAccessComponents;
import entity.CommonUserFactory;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordPresenter;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;

/**
 * Builder for change password use case.
 */
public class ChangePasswordUseCaseBuilder extends AbstractUseCaseBuilder {
    private final CommonUserFactory userFactory;

    public ChangePasswordUseCaseBuilder(Views views, DataAccessComponents dataAccess, CommonUserFactory userFactory) {
        super(views, dataAccess);
        this.userFactory = userFactory;
    }

    /**
     * Builds the change password related use case.
     */
    public void build() {
        final ChangePasswordOutputBoundary outputBoundary = new ChangePasswordPresenter(
                getViews().getAuth().getViewModels().getLoggedInViewModel());

        final ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(
                getDataAccess().getUserDataAccess(),
                outputBoundary,
                userFactory);

        final ChangePasswordController controller = new ChangePasswordController(interactor);
        getViews().getAuth().getViews().getLoggedInView().setChangePasswordController(controller);
    }
}
