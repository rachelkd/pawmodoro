package app.factory;

import app.service.DialogService;
import interface_adapter.adoption.AdoptionViewModel;
import interface_adapter.break_session.BreakSessionState;
import interface_adapter.break_session.BreakSessionViewModel;
import interface_adapter.cat.CatViewModel;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.create_inventory.InventoryViewModel;
import interface_adapter.display_cat_image.DisplayCatImageViewModel;
import interface_adapter.display_cat_stats.DisplayCatStatsViewModel;
import interface_adapter.display_cat_stats.DisplayCatStatsViewModelFactory;
import interface_adapter.get_cat_fact.GetCatFactViewModel;
import interface_adapter.initialize_cats.CatViewFactory;
import interface_adapter.initialize_cats.InitializeCatsViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.maxcatserror.MaxCatsErrorViewModel;
import interface_adapter.runawaycat.RunawayCatViewModel;
import interface_adapter.setupsession.SetupSessionViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.study_session.StudySessionViewModel;
import view.*;

/**
 * The ViewFactory class.
 * Responsible for creating various view components in the application.
 */
public class ViewFactory {
    private final CatViewFactory catViewModelFactory = new CatViewFactory();
    private final DisplayCatStatsViewModelFactory displayCatStatsViewModelFactory =
            new DisplayCatStatsViewModelFactory();

    /**
     * Creates a Login View.
     * @param loginViewModel the login view model
     * @return LoginView
     */
    public LoginView createLoginView(LoginViewModel loginViewModel) {
        return new LoginView(loginViewModel);
    }

    /**
     * Creates a Signup View.
     * @param signupViewModel the signup view model
     * @return SignupView
     */
    public SignupView createSignupView(SignupViewModel signupViewModel) {
        return new SignupView(signupViewModel);
    }

    /**
     * Creates a Logged In View with associated components.
     * @param loggedInViewModel the logged in view model
     * @return LoggedInView
     */
    public LoggedInView createLoggedInView(LoggedInViewModel loggedInViewModel) {
        return new LoggedInView(loggedInViewModel);
    }

    /**
     * Creates an Adoption View.
     * @param adoptionViewModel the adoption view model
     * @param dialogService the dialog service for user interactions
     * @return AdoptionView
     */
    public AdoptionView createAdoptionView(AdoptionViewModel adoptionViewModel, DialogService dialogService) {
        dialogService.createAdoptionDialog(adoptionViewModel);
        return (AdoptionView) dialogService.getAdoptionDialog();
    }

    /**
     * Creates a Runaway Cat View.
     * @param runawayCatViewModel the runaway cat view model
     * @param dialogService the dialog service for user interactions
     * @return RunawayCatView
     */
    public RunawayCatView createRunawayCatView(RunawayCatViewModel runawayCatViewModel, DialogService dialogService) {
        dialogService.createRunawayCatDialog(runawayCatViewModel);
        return (RunawayCatView) dialogService.getRunawayCatDialog();
    }

    /**
     * Creates a Max Cats Error View.
     * @param maxCatsErrorViewModel the max cats error view model
     * @return MaxCatsErrorView
     */
    public MaxCatsErrorView createMaxCatsErrorView(MaxCatsErrorViewModel maxCatsErrorViewModel) {
        return new MaxCatsErrorView(maxCatsErrorViewModel);
    }

    /**
     * Creates a Display Cat Image View.
     * @param displayCatImageViewModel the display cat image view model
     * @return DisplayCatImageView
     */
    public DisplayCatImageView createDisplayCatImageView(DisplayCatImageViewModel displayCatImageViewModel) {
        return new DisplayCatImageView(displayCatImageViewModel);
    }

    /**
     * Creates a Setup Session View.
     * @param setupSessionViewModel the setup session view model
     * @return SetupSessionView
     */
    public SetupSessionView createSetupSessionView(SetupSessionViewModel setupSessionViewModel) {
        return new SetupSessionView(setupSessionViewModel);
    }

    /**
     * Creates a Study Session View.
     * @param studySessionViewModel the study session view model
     * @param breakSessionViewModel the break session view model
     * @param initializeCatsViewModel the initialize cat view model
     * @param dialogService the dialog service
     * @param catContainerView the existing cat container view instance
     * @return StudySessionView
     */
    public StudySessionView createStudySessionView(StudySessionViewModel studySessionViewModel,
                                                   BreakSessionViewModel breakSessionViewModel,
                                                   InitializeCatsViewModel initializeCatsViewModel,
                                                   DialogService dialogService,
                                                   CatContainerView catContainerView) {
        return new StudySessionView(studySessionViewModel, breakSessionViewModel,
                initializeCatsViewModel, dialogService, catContainerView);
    }

    /**
     * Creates an Inventory View.
     * @param inventoryViewModel the inventory view model
     * @param dialogService the dialog service for user interactions
     * @return InventoryView
     */
    public InventoryView createInventoryView(InventoryViewModel inventoryViewModel, DialogService dialogService) {
        // have the dialog run in the background so it listens for changes, but by default won't be visible
        dialogService.createInventoryDialog(inventoryViewModel);
        return dialogService.getInventoryDialog();
    }

    /**
     * Creates a Cat View with associated components.
     * @param catViewModel the cat view model
     * @param displayCatStatsViewModel the display cat stats view model
     * @param inventoryViewModel the inventory view model
     * @param dialogService the dialog service
     * @param getCatFactView the get cat fact view
     * @return CatView
     */
    public CatView createCatView(CatViewModel catViewModel,
            DisplayCatStatsViewModel displayCatStatsViewModel,
            InventoryViewModel inventoryViewModel,
            DialogService dialogService,
            GetCatFactView getCatFactView) {
        return new CatView(catViewModel, displayCatStatsViewModel, inventoryViewModel,
                dialogService, getCatFactView);
    }

    /**
     * Creates a Cat Container View.
     * @param initializeCatsViewModel the initialize cats view model
     * @param inventoryViewModel the inventory view model
     * @param dialogService the dialog service
     * @param getCatFactView get cat fact view
     * @return CatContainer View
     */
    public CatContainerView createCatContainerView(InitializeCatsViewModel initializeCatsViewModel,
                                                   InventoryViewModel inventoryViewModel,
                                                   DialogService dialogService,
                                                   GetCatFactView getCatFactView) {
        return new CatContainerView(initializeCatsViewModel, inventoryViewModel, dialogService, getCatFactView,
                catViewModelFactory, displayCatStatsViewModelFactory);
    }

    /**
     * Creates a Get Cat Fact View.
     * @param getCatFactViewModel the get cat fact view model
     * @return GetCatFactView
     */
    public GetCatFactView createGetCatFactView(GetCatFactViewModel getCatFactViewModel) {
        return new GetCatFactView(getCatFactViewModel);
    }

    /**
     * Creates a Display Cat Stats View.
     * @param displayCatStatsViewModel the display cat stats view model
     * @param inventoryViewModel the inventory view model
     * @param getCatFactView the get cat fact view
     * @param dialogService the dialog service
     * @return DisplayCatStatsView
     */
    public DisplayCatStatsView createDisplayCatStatsView(
            DisplayCatStatsViewModel displayCatStatsViewModel,
            InventoryViewModel inventoryViewModel,
            GetCatFactView getCatFactView,
            DialogService dialogService) {
        // Create dialog in background like inventory
        dialogService.createDisplayCatStatsDialog(displayCatStatsViewModel, inventoryViewModel, getCatFactView);
        return dialogService.getDisplayCatStatsDialog();
    }

    /**
     * Creates Break Session View.
     * @param breakSessionViewModel the break session view model
     * @param breakSessionState     the break session state
     * @param adoptionViewModel the adoption view model
     * @param dialogService the dialog service
     * @param catContainerView the cat container view
     * @return BreakSessionView
     */
    public BreakSessionView createBreakSessionView(BreakSessionViewModel breakSessionViewModel,
                                                   BreakSessionState breakSessionState,
                                                   AdoptionViewModel adoptionViewModel,
                                                   DialogService dialogService,
                                                   CatContainerView catContainerView) {
        return new BreakSessionView(breakSessionViewModel,
                breakSessionState, adoptionViewModel, dialogService, catContainerView);
    }
}
