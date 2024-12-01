package app.factory;

import app.service.DialogService;
import interface_adapter.adoption.AdoptionViewModel;
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
import interface_adapter.music_control.MusicControlViewModel;
import interface_adapter.runawaycat.RunawayCatViewModel;
import interface_adapter.setupsession.SetupSessionViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.study_session.StudySessionViewModel;
import view.AdoptionView;
import view.BreakSessionView;
import view.CatContainerView;
import view.CatView;
import view.DisplayCatImageView;
import view.DisplayCatStatsView;
import view.GetCatFactView;
import view.InventoryView;
import view.LoggedInView;
import view.LoginView;
import view.RunawayCatView;
import view.SetupSessionView;
import view.SignupView;
import view.StudySessionView;

/**
 * The ViewFactory class.
 * Facade for coordinating view creation across different specialized factories.
 */
public class ViewFactory {
    private final LoginViewFactory loginViewFactory;
    private final SessionViewFactory sessionViewFactory;
    private final DialogViewFactory dialogViewFactory;
    private final CatRelatedViewFactory catRelatedViewFactory;
    private final CatViewFactory catViewFactory;
    private final DisplayCatStatsViewModelFactory displayCatStatsViewModelFactory;

    public ViewFactory(DialogService dialogService) {
        this.loginViewFactory = new LoginViewFactory();
        this.sessionViewFactory = new SessionViewFactory();
        this.dialogViewFactory = new DialogViewFactory(dialogService);
        this.catViewFactory = new CatViewFactory();
        this.displayCatStatsViewModelFactory = new DisplayCatStatsViewModelFactory();
        this.catRelatedViewFactory = new CatRelatedViewFactory(catViewFactory, displayCatStatsViewModelFactory);

    }

    /**
     * Delegates to loginViewFactory to create a LoginView.
     * @param loginViewModel the login view model
     * @return LoginView
     */
    public LoginView createLoginView(LoginViewModel loginViewModel) {
        return loginViewFactory.createLoginView(loginViewModel);
    }

    /**
     * Delegates to loginViewFactory to create a SignupView.
     * @param signupViewModel the signup view model
     * @return SignupView
     */
    public SignupView createSignupView(SignupViewModel signupViewModel) {
        return loginViewFactory.createSignupView(signupViewModel);
    }

    /**
     * Delegates to loginViewFactory to create a LoggedInView.
     * @param loggedInViewModel the logged in view model
     * @return LoggedInView
     */
    public LoggedInView createLoggedInView(LoggedInViewModel loggedInViewModel) {
        return loginViewFactory.createLoggedInView(loggedInViewModel);
    }

    /**
     * Delegates to sessionViewFactory to create a Setup Session View.
     * @param setupSessionViewModel the setup session view model
     * @return SetupSessionView
     */
    public SetupSessionView createSetupSessionView(SetupSessionViewModel setupSessionViewModel) {
        return sessionViewFactory.createSetupSessionView(setupSessionViewModel);
    }

    /**
     * Delegates to sessionViewFactory to create a Study Session View.
     * @param studySessionViewModel the study session view model
     * @param breakSessionViewModel the break session view model
     * @param initializeCatsViewModel the initialize cat view model
     * @param dialogService the dialog service
     * @param catContainerView the existing cat container view instance
     * @param musicControlViewModel the music control view model
     * @return StudySessionView
     */
    public StudySessionView createStudySessionView(StudySessionViewModel studySessionViewModel,
                                                   BreakSessionViewModel breakSessionViewModel,
                                                   InitializeCatsViewModel initializeCatsViewModel,
                                                   DialogService dialogService,
                                                   CatContainerView catContainerView,
                                                   MusicControlViewModel musicControlViewModel) {
        return sessionViewFactory.createStudySessionView(studySessionViewModel, breakSessionViewModel,
                initializeCatsViewModel, dialogService, catContainerView, musicControlViewModel);
    }

    /**
     * Delegates to sessionViewFactory to create Break Session View.
     * @param breakSessionViewModel the break session view model
     * @param adoptionViewModel the adoption view model
     * @param dialogService the dialog service
     * @param displayCatImageView the cat image view
     * @param catContainerView the cat container view
     * @return BreakSessionView
     */
    public BreakSessionView createBreakSessionView(BreakSessionViewModel breakSessionViewModel,
                                                   AdoptionViewModel adoptionViewModel,
                                                   DialogService dialogService,
                                                   DisplayCatImageView displayCatImageView,
                                                   CatContainerView catContainerView) {
        return sessionViewFactory.createBreakSessionView(breakSessionViewModel, adoptionViewModel,
                dialogService, displayCatImageView, catContainerView);
    }

    /**
     * Delegates to dialogViewFactory to create an Adoption View.
     * @param adoptionViewModel the adoption view model
     * @return AdoptionView
     */
    public AdoptionView createAdoptionView(AdoptionViewModel adoptionViewModel) {
        return dialogViewFactory.createAdoptionView(adoptionViewModel);
    }

    /**
     * Delegates to dialogViewFactory to create a Runaway Cat View.
     * @param runawayCatViewModel the runaway cat view model
     * @return RunawayCatView
     */
    public RunawayCatView createRunawayCatView(RunawayCatViewModel runawayCatViewModel) {
        return dialogViewFactory.createRunawayCatView(runawayCatViewModel);
    }

    /**
     * Delegates to dialogViewFactory to create an Inventory View.
     * @param inventoryViewModel the inventory view model
     * @return InventoryView
     */
    public InventoryView createInventoryView(InventoryViewModel inventoryViewModel) {
        return dialogViewFactory.createInventoryView(inventoryViewModel);
    }

    /**
     * Delegates to dialogViewFactory to create a Display Cat Stats View.
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
        return dialogViewFactory.createDisplayCatStatsView(displayCatStatsViewModel,
                inventoryViewModel, getCatFactView);
    }

    /**
     * Delegates to CatRelatedViewFactory to create a Cat View with associated components.
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
        return catRelatedViewFactory.createCatView(catViewModel, displayCatStatsViewModel,
                inventoryViewModel, dialogService, getCatFactView);
    }

    /**
     * Delegates to CatRelatedViewFactory to create a Cat Container View.
     * @param initializeCatsViewModel the initialize cats view model
     * @param inventoryViewModel the inventory view model
     * @param dialogService the dialog service
     * @param getCatFactView the get cat fact view
     * @return CatContainerView
     */
    public CatContainerView createCatContainerView(InitializeCatsViewModel initializeCatsViewModel,
                                                InventoryViewModel inventoryViewModel,
                                                DialogService dialogService,
                                                GetCatFactView getCatFactView) {
        return catRelatedViewFactory.createCatContainerView(initializeCatsViewModel,
                inventoryViewModel, dialogService, getCatFactView);
    }

    /**
     * Delegates to CatRelatedViewFactory to create a Get Cat Fact View.
     * @param getCatFactViewModel the get cat fact view model
     * @return GetCatFactView
     */
    public GetCatFactView createGetCatFactView(GetCatFactViewModel getCatFactViewModel) {
        return catRelatedViewFactory.createGetCatFactView(getCatFactViewModel);
    }

    /**
     * Delegates to CatRelatedViewFactory to create a Display Cat Image View.
     * @param displayCatImageViewModel the display cat image view model
     * @return DisplayCatImageView
     */
    public DisplayCatImageView createDisplayCatImageView(DisplayCatImageViewModel displayCatImageViewModel) {
        return catRelatedViewFactory.createDisplayCatImageView(displayCatImageViewModel);
    }
}
