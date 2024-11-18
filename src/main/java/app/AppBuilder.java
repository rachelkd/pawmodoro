package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


import data_access.*; // TODO: Figure out all the proper imports because this keeps causing Checkstyle errors.
import data_access.ApiDisplayCatImageDataAccessObject;
import data_access.DBUserDataAccessObject;
import data_access.InMemoryInventoryDataAccessObject;
import data_access.InMemoryTimerDataAccessObject;
import entity.*; // TODO: Import the correct entity package
import interface_adapter.ViewManagerModel;
import interface_adapter.add_to_inventory.AddToInventoryController;
import interface_adapter.add_to_inventory.AddToInventoryPresenter;
import interface_adapter.adoption.AdoptionViewModel;
import interface_adapter.cat.CatViewModel;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordPresenter;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.create_inventory.CreateInventoryController;
import interface_adapter.create_inventory.CreateInventoryPresenter;
import interface_adapter.create_inventory.InventoryViewModel;
import interface_adapter.display_cat_image.DisplayCatImageController;
import interface_adapter.display_cat_image.DisplayCatImagePresenter;
import interface_adapter.display_cat_image.DisplayCatImageViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.maxcatserror.MaxCatsErrorController;
import interface_adapter.maxcatserror.MaxCatsErrorPresenter;
import interface_adapter.maxcatserror.MaxCatsErrorViewModel;
import interface_adapter.setupsession.SetupSessionController;
import interface_adapter.setupsession.SetupSessionPresenter;
import interface_adapter.setupsession.SetupSessionViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.timer.TimerController;
import interface_adapter.timer.TimerPresenter;
import interface_adapter.timer.TimerViewModel;
import interface_adapter.use_item_in_inventory.UseItemController;
import interface_adapter.use_item_in_inventory.UseItemPresenter;
import use_case.adoption.AdoptionDataAccessInterface;
import use_case.adoption.AdoptionInputBoundary;
import use_case.adoption.AdoptionInteractor;
import use_case.adoption.AdoptionOutputBoundary;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.create_inventory.CreateInventoryInputBoundary;
import use_case.create_inventory.CreateInventoryInteractor;
import use_case.create_inventory.CreateInventoryOutputBoundary;
import use_case.display_cat_image.DisplayCatImageDataAccessInterface;
import use_case.display_cat_image.DisplayCatImageInputBoundary;
import use_case.display_cat_image.DisplayCatImageInteractor;
import use_case.display_cat_image.DisplayCatImageOutputBoundary;
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
import use_case.maxcatserror.MaxCatsErrorInputBoundary;
import use_case.maxcatserror.MaxCatsErrorInteractor;
import use_case.maxcatserror.MaxCatsErrorOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.timer.display_timer.DisplayTimerInputBoundary;
import use_case.timer.display_timer.DisplayTimerInteractor;
import use_case.timer.display_timer.DisplayTimerOutputBoundary;
import view.*;  // TODO: Import the correct view package
import view.DisplayCatImageView;

// TODO: Fix order of imports when all packages are created
import use_case.setupsession.SetupSessionInputBoundary;
import use_case.setupsession.SetupSessionInteractor;
import use_case.setupsession.SetupSessionOutputBoundary;
import view.AdoptionView;
// TODO: Might need to add more builders to reduce coupling (getting Checkstyle warnings)

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

    private final CatImageFactory catImageFactory = new CatImageFactory();
    private final FoodItemFactory foodItemFactory = new FoodItemFactory();
    private final InventoryFactory inventoryFactory = new FoodInventoryFactory();
    private final UserFactory userFactory = new CommonUserFactory();

    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private final DBUserDataAccessObject userDataAccessObject = new DBUserDataAccessObject(userFactory);
    private final InMemoryInventoryDataAccessObject inventoryDataAccessObject = new InMemoryInventoryDataAccessObject();
    private final DisplayCatImageDataAccessInterface displayCatImageDataAccessObject =
            new ApiDisplayCatImageDataAccessObject(catImageFactory);

    private InventoryViewModel inventoryViewModel;
    private InventoryView inventoryView;

    private SignupView signupView;
    private SignupViewModel signupViewModel;

    private SetupSessionView setupSessionView;
    private SetupSessionViewModel setupSessionViewModel;

    private AdoptionView adoptionView;
    private AdoptionViewModel adoptionViewModel;
    private final AdoptionDataAccessObject adoptionDataAccessObject = new AdoptionDataAccessObject();

    private MaxCatsErrorView maxCatsErrorView;
    private MaxCatsErrorViewModel maxCatsErrorViewModel;

    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private LoggedInView loggedInView;
    private LoginView loginView;

    private final TimerViewModel timerViewModel = new TimerViewModel();
    private final InMemoryTimerDataAccessObject timerDataAccessObject = new InMemoryTimerDataAccessObject();
    private final TimerFactory timerFactory = new TimerFactory();

    private DisplayCatImageViewModel displayCatImageViewModel;
    private DisplayCatImageView displayCatImageView;

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
     * Adds the Adoption View to the application.
     *
     * @return this builder
     */
    public AppBuilder addAdoptionView() {
        adoptionViewModel = new AdoptionViewModel();
        adoptionView = new AdoptionView(adoptionViewModel);
        cardPanel.add(adoptionView, adoptionView.getViewName());
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
        final CatViewModel catViewModel = new CatViewModel();
        loggedInView = new LoggedInView(loggedInViewModel, timerViewModel, catViewModel);
        cardPanel.add(loggedInView, loggedInView.getViewName());
        return this;
    }

    /**
     * Add the inventory view to the application.
     * 
     * @return this builder
     */
    public AppBuilder addInventoryView() {
        inventoryViewModel = new InventoryViewModel();
        inventoryView = new InventoryView(inventoryViewModel);
        cardPanel.add(inventoryView, inventoryViewModel.getViewName());
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
     * Adds the setup session Use Case to the application.
     *
     * @return this builder
     */
    public AppBuilder addSetupSessionUseCase() {
        final SetupSessionOutputBoundary setupSessionOutputBoundary =
                new SetupSessionPresenter(setupSessionViewModel, viewManagerModel, timerViewModel);
        final SetupSessionInputBoundary setupInteractor = new SetupSessionInteractor(setupSessionOutputBoundary);
        final SetupSessionController setupController = new SetupSessionController(setupInteractor);
        setupSessionView.setSetupSessionController(setupController);
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
                viewManagerModel, inventoryViewModel);

        final CreateInventoryInputBoundary createInventoryInteractor = new CreateInventoryInteractor(
                inventoryDataAccessObject, createInventoryOutputBoundary, inventoryFactory);

        final CreateInventoryController createInventoryController = new CreateInventoryController(
                createInventoryInteractor);
        inventoryView.setCreateInventoryController(createInventoryController);
        return this;
    }

    /**
     * Adds the Add To Inventory Use Case to the application.
     * 
     * @return this builder
     */
    public AppBuilder addAddToInventoryUseCase() {
        final AddToInventoryOutputBoundary addToInventoryOutputBoundary = new AddToInventoryPresenter(
                inventoryViewModel);

        final AddToInventoryInputBoundary addToInventoryInteractor = new AddToInventoryInteractor(
                inventoryDataAccessObject,
                addToInventoryOutputBoundary, foodItemFactory);

        final AddToInventoryController addToInventoryController = new AddToInventoryController(
                addToInventoryInteractor);
        inventoryView.setAddToInventoryController(addToInventoryController);
        return this;
    }

    /**
     * Adds the Use Item Use Case to the application.
     * 
     * @return this builder
     */
    public AppBuilder addUseItemUseCase() {
        final UseItemOutputBoundary useItemOutputBoundary = new UseItemPresenter(inventoryViewModel);

        final UseItemInputBoundary useItemInteractor = new UseItemInteractor(inventoryDataAccessObject,
                useItemOutputBoundary);

        final UseItemController useItemController = new UseItemController(useItemInteractor);
        inventoryView.setUseItemController(useItemController);
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
     * Adds the max cats error use case to the application
     *
     * @ return this builder
     */
    public AppBuilder addMaxCatsUseCase() {
        final MaxCatsErrorOutputBoundary maxCatsErrorOutputBoundary = new MaxCatsErrorPresenter(viewManagerModel,
                maxCatsErrorViewModel);
        final MaxCatsErrorInputBoundary maxCatsInteractor = new MaxCatsErrorInteractor(maxCatsErrorOutputBoundary);
        final MaxCatsErrorController maxCatsController = new MaxCatsErrorController(maxCatsInteractor);
        maxCatsErrorView.setMaxCatsController(maxCatsController);
        return this;
    }
    /**
     * Adds the adoption use case to the application.
     *
     * @return this builder
     */
    public AppBuilder addAdoptionUseCase() {
        final AdoptionOutputBoundary adoptionOutputBoundary = new AdoptionPresenter(setupSessionViewModel,
                adoptionViewModel, viewManagerModel);
        final AdoptionInputBoundary adoptionInteractor = new AdoptionInteractor(adoptionDataAccessObject,
                adoptionOutputBoundary);
        final AdoptionController adoptionController = new AdoptionController(adoptionInteractor);
        adoptionView.setAdoptionController(adoptionController);
        return this;
    }

    /**
     * Adds the Display Timer Use Case to the application.
     * 
     * @return this builder
     */
    public AppBuilder addDisplayTimerUseCase() {
        final DisplayTimerOutputBoundary timerOutputBoundary = new TimerPresenter(timerViewModel);

        final DisplayTimerInputBoundary timerInteractor = new DisplayTimerInteractor(timerDataAccessObject,
                timerOutputBoundary);

        final TimerController timerController = new TimerController(timerInteractor);

        // If LoggedInView needs to trigger timer updates
        if (loggedInView != null) {
            loggedInView.setTimerController(timerController);
        }

        return this;
    }

    /**
     * Adds the display cat image view to the application with refresh
     * functionality.
     *
     * @return this builder
     */
    public AppBuilder addDisplayCatImageWithRefreshView() {
        displayCatImageViewModel = new DisplayCatImageViewModel();
        displayCatImageViewModel.setRefreshAllowed(true);
        displayCatImageView = new DisplayCatImageView(displayCatImageViewModel);

        loggedInView.addCatImageView(displayCatImageView);
        return this;
    }

    /**
     * Adds the display cat image view to the application without refresh
     * functionality.
     *
     * @return this builder
     */
    public AppBuilder addDisplayCatImageView() {
        displayCatImageViewModel = new DisplayCatImageViewModel();
        displayCatImageViewModel.setRefreshAllowed(false);
        displayCatImageView = new DisplayCatImageView(displayCatImageViewModel);

        loggedInView.addCatImageView(displayCatImageView);
        return this;
    }

    /**
     * Adds the display cat image use case to the application.
     * Refresh functionality depends on view model.
     *
     * @return this builder
     */
    public AppBuilder addDisplayCatImageUseCase() {
        final DisplayCatImageOutputBoundary displayCatImageOutputBoundary = new DisplayCatImagePresenter(
                displayCatImageViewModel);
        final DisplayCatImageInputBoundary displayCatImageInteractor = new DisplayCatImageInteractor(
                displayCatImageDataAccessObject, displayCatImageOutputBoundary);

        final DisplayCatImageController displayCatImageController = new DisplayCatImageController(
                displayCatImageInteractor);

        displayCatImageView.setDisplayCatImageController(displayCatImageController);

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
