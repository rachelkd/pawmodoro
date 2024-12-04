package app.builder.usecase;

import app.builder.view.Views;
import app.components.DataAccessComponents;
import entity.CommonUserFactory;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;

/**
 * Builder for signup use case.
 */
public class SignupUseCaseBuilder extends AbstractUseCaseBuilder {
    private final CommonUserFactory userFactory;

    public SignupUseCaseBuilder(Views views, DataAccessComponents dataAccess, CommonUserFactory userFactory) {
        super(views, dataAccess);
        this.userFactory = userFactory;
    }

    /**
     * Builds the signup use case.
     */
    public void build() {
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
    }
}
