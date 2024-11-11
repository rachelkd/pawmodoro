package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.InMemoryInventoryDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.FoodInventoryFactory;
import entity.FoodItemFactory;
import entity.InventoryFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_to_inventory.AddToInventoryController;
import interface_adapter.add_to_inventory.AddToInventoryPresenter;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordPresenter;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.create_inventory.CreateInventoryController;
import interface_adapter.create_inventory.CreateInventoryPresenter;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.maxcatserror.MaxCatsErrorViewModel;
import interface_adapter.setupsession.SetupSessionController;
import interface_adapter.setupsession.SetupSessionPresenter;
import interface_adapter.setupsession.SetupSessionViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.use_item_in_inventory.UseItemController;
import interface_adapter.use_item_in_inventory.UseItemPresenter;
import use_case.authentication.create_inventory.CreateInventoryInputBoundary;
import use_case.authentication.create_inventory.CreateInventoryInteractor;
import use_case.authentication.create_inventory.CreateInventoryOutputBoundary;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.food_management.add_to_inventory.AddToInventoryInputBoundary;
import use_case.food_management.add_to_inventory.AddToInventoryInteractor;
import use_case.food_management.add_to_inventory.AddToInventoryOutputBoundary;
import use_case.food_management.use_item_in_inventory.UseItemInputBoundary;
import use_case.food_management.use_item_in_inventory.UseItemInteractor;
import use_case.food_management.use_item_in_inventory.UseItemOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.LoggedInView;
import view.LoginView;
import view.MaxCatsErrorView;
import view.SetupSessionView;
import view.SignupView;
import view.ViewManager;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * This is done by adding each View and then adding related Use Cases.
 */
// Checkstyle note: you can ignore the "Class Data Abstraction Coupling"
// and the "Class Fan-Out Complexity" issues for this lab; we encourage
// your team to think about ways to refactor the code to resolve these
// if your team decides to work with this as your starter code
// for your final project this term.
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    // thought question: is the hard dependency below a problem?
    private final FoodItemFactory foodItemFactory = new FoodItemFactory();
    private final InventoryFactory inventoryFactory = new FoodInventoryFactory();
    private final UserFactory userFactory = new CommonUserFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // thought question: is the hard dependency below a problem?
    private final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
    private final InMemoryInventoryDataAccessObject inventoryDataAccessObject = new InMemoryInventoryDataAccessObject();

    private SignupView signupView;
    private SignupViewModel signupViewModel;

    private SetupSessionView setupSessionView;
    private SetupSessionViewModel setupSessionViewModel;

    private MaxCatsErrorView maxCatsErrorView;
    private MaxCatsErrorViewModel maxCatsErrorViewModel;

    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private LoggedInView loggedInView;
    private LoginView loginView;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Signup View to the application.
     * 
     * @return this builder
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Adds the Setup Study Session View to the application.
     * 
     * @return this builder
     */
    public AppBuilder addSetupSessionView() {
        setupSessionViewModel = new SetupSessionViewModel();
        setupSessionView = new SetupSessionView(setupSessionViewModel);
        cardPanel.add(setupSessionView, setupSessionView.getViewName());
        return this;
    }

    /**
     * Adds the Maximum Cats Error View to the application.
     * 
     * @return this builder
     */
    public AppBuilder addMaxCatsErrorView() {
        maxCatsErrorViewModel = new MaxCatsErrorViewModel();
        maxCatsErrorView = new MaxCatsErrorView(maxCatsErrorViewModel);
        cardPanel.add(maxCatsErrorView, maxCatsErrorView.getViewName());

        return this;
    }

    /**
     * Adds the Login View to the application.
     * 
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the LoggedIn View to the application.
     * 
     * @return this builder
     */
    public AppBuilder addLoggedInView() {
        loggedInViewModel = new LoggedInViewModel();
        loggedInView = new LoggedInView(loggedInViewModel);
        cardPanel.add(loggedInView, loggedInView.getViewName());
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * 
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        final SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     * 
     * @return this builder
     */
    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    /**
     * Adds the Change Password Use Case to the application.
     * 
     * @return this builder
     */
    public AppBuilder addChangePasswordUseCase() {
        final ChangePasswordOutputBoundary changePasswordOutputBoundary = new ChangePasswordPresenter(
                loggedInViewModel);

        final ChangePasswordInputBoundary changePasswordInteractor = new ChangePasswordInteractor(userDataAccessObject,
                changePasswordOutputBoundary, userFactory);

        final ChangePasswordController changePasswordController = new ChangePasswordController(
                changePasswordInteractor);
        loggedInView.setChangePasswordController(changePasswordController);
        return this;
    }

    /**
     * Adds the Create Inventory Use Case to the application.
     * 
     * @return this builder
     */
    public AppBuilder addCreateInventoryUseCase() {
        final CreateInventoryOutputBoundary createInventoryOutputBoundary = new CreateInventoryPresenter(
                loggedInViewModel);

        final CreateInventoryInputBoundary createInventoryInteractor = new CreateInventoryInteractor(
                inventoryDataAccessObject, createInventoryOutputBoundary, inventoryFactory);

        final CreateInventoryController createInventoryController = new CreateInventoryController(
                createInventoryInteractor);
        loggedInView.setCreateInventoryController(createInventoryController);
        return this;
    }

    /**
     * Adds the Add To Inventory Use Case to the application.
     * 
     * @return this builder
     */
    public AppBuilder addAddToInventoryUseCase() {
        final AddToInventoryOutputBoundary addToInventoryOutputBoundary = new AddToInventoryPresenter(
                loggedInViewModel);

        final AddToInventoryInputBoundary addToInventoryInteractor = new AddToInventoryInteractor(
                inventoryDataAccessObject,
                addToInventoryOutputBoundary, foodItemFactory);

        final AddToInventoryController addToInventoryController = new AddToInventoryController(
                addToInventoryInteractor);
        loggedInView.setAddToInventoryController(addToInventoryController);
        return this;
    }

    /**
     * Adds the Use Item Use Case to the application.
     * 
     * @return this builder
     */
    public AppBuilder addUseItemUseCase() {
        final UseItemOutputBoundary useItemOutputBoundary = new UseItemPresenter(loggedInViewModel);

        final UseItemInputBoundary useItemInteractor = new UseItemInteractor(inventoryDataAccessObject,
                useItemOutputBoundary);

        final UseItemController useItemController = new UseItemController(useItemInteractor);
        loggedInView.setUseItemController(useItemController);
        return this;
    }

    /**
     * Adds the Logout Use Case to the application.
     * 
     * @return this builder
     */
    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel);

        final LogoutInputBoundary logoutInteractor = new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        loggedInView.setLogoutController(logoutController);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the SignupView to
     * be displayed.
     * 
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(signupView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
