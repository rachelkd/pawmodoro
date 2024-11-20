package app.factory;

import java.util.Set;

import app.service.DialogService;
import interface_adapter.adoption.AdoptionViewModel;
import interface_adapter.cat.CatViewModel;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.create_inventory.InventoryViewModel;
import interface_adapter.display_cat_image.DisplayCatImageViewModel;
import interface_adapter.display_cat_stats.DisplayCatStatsViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.maxcatserror.MaxCatsErrorViewModel;
import interface_adapter.runawaycat.RunawayCatViewModel;
import interface_adapter.setupsession.SetupSessionViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.study_session.StudySessionViewModel;
import interface_adapter.timer.TimerViewModel;
import view.AdoptionView;
import view.CatView;
import view.DisplayCatImageView;
import view.DisplayCatStatsView;
import view.InventoryView;
import view.LoggedInView;
import view.LoginView;
import view.MaxCatsErrorView;
import view.RunawayCatView;
import view.SetupSessionView;
import view.SignupView;
import view.StudySessionView;

/**
 * Default implementation of ViewFactory.
 * Responsible for creating various view components in the application.
 */
public class ViewFactory {

    /**
     * Creates a Login View.
     * 
     * @param loginViewModel the login view model
     * @return LoginView
     */
    public LoginView createLoginView(LoginViewModel loginViewModel) {
        return new LoginView(loginViewModel);
    }

    /**
     * Creates a Signup View.
     * 
     * @param signupViewModel the signup view model
     * @return SignupView
     */
    public SignupView createSignupView(SignupViewModel signupViewModel) {
        return new SignupView(signupViewModel);
    }

    /**
     * Creates a Logged In View with associated components.
     * 
     * @param loggedInViewModel the logged in view model
     * @param timerViewModel the timer view model
     * @param catViewModel the cat view model
     * @param displayCatStatsViewModel the display cat stats view model
     * @return LoggedInView
     */
    public LoggedInView createLoggedInView(LoggedInViewModel loggedInViewModel) {
        return new LoggedInView(loggedInViewModel);
    }

    /**
     * Creates an Adoption View.
     * 
     * @param adoptionViewModel the adoption view model
     * @return AdoptionView
     */
    public AdoptionView createAdoptionView(AdoptionViewModel adoptionViewModel) {
        return new AdoptionView(adoptionViewModel);
    }

    /**
     * Creates a Runaway Cat View.
     * 
     * @param runawayCatViewModel the runaway cat view model
     * @return RunawayCatView
     */
    public RunawayCatView createRunawayCatView(RunawayCatViewModel runawayCatViewModel) {
        return new RunawayCatView(runawayCatViewModel);
    }

    /**
     * Creates a Max Cats Error View.
     * 
     * @param maxCatsErrorViewModel the max cats error view model
     * @return MaxCatsErrorView
     */
    public MaxCatsErrorView createMaxCatsErrorView(MaxCatsErrorViewModel maxCatsErrorViewModel) {
        return new MaxCatsErrorView(maxCatsErrorViewModel);
    }

    /**
     * Creates a Display Cat Image View.
     * 
     * @param displayCatImageViewModel the display cat image view model
     * @return DisplayCatImageView
     */
    public DisplayCatImageView createDisplayCatImageView(DisplayCatImageViewModel displayCatImageViewModel) {
        return new DisplayCatImageView(displayCatImageViewModel);
    }

    /**
     * Creates a Setup Session View.
     * 
     * @param setupSessionViewModel the setup session view model
     * @return SetupSessionView
     */
    public SetupSessionView createSetupSessionView(SetupSessionViewModel setupSessionViewModel) {
        return new SetupSessionView(setupSessionViewModel);
    }

    /**
     * Creates a Study Session View.
     * 
     * @param studySessionViewModel the study session view model
     * @param timerSessionViewModel the timer session view model
     * @return StudySessionView
     */
    public StudySessionView createStudySessionView(StudySessionViewModel studySessionViewModel,
            TimerViewModel timerSessionViewModel) {
        return new StudySessionView(studySessionViewModel, timerSessionViewModel);
    }

    /**
     * Creates an Inventory View.
     * 
     * @param inventoryViewModel the inventory view model
     * @return InventoryView
     */
    public InventoryView createInventoryView(InventoryViewModel inventoryViewModel) {
        return new InventoryView(inventoryViewModel);
    }

    /**
     * Creates a Cat View with associated components.
     * 
     * @param catViewModel the cat view model
     * @param displayCatStatsViewModel the display cat stats view model
     * @param dialogService the dialog service for user interactions
     * @return CatView
     */
    public CatView createCatView(CatViewModel catViewModel,
            DisplayCatStatsViewModel displayCatStatsViewModel,
            DialogService dialogService) {
        return new CatView(catViewModel, displayCatStatsViewModel, dialogService);
    }

    /**
     * Creates a Display Cat Stats View.
     * 
     * @param displayCatStatsViewModel the display cat stats view model
     * @return DisplayCatStatsView
     */
    public DisplayCatStatsView createDisplayCatStatsView(DisplayCatStatsViewModel displayCatStatsViewModel) {
        // return new DisplayCatStatsView(displayCatStatsViewModel);
        return null;
    }
}
