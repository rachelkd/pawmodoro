package app.factory;

import interface_adapter.adoption.AdoptionViewModel;
import interface_adapter.cat.CatViewModel;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.create_inventory.InventoryViewModel;
import interface_adapter.display_cat_image.DisplayCatImageViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.maxcatserror.MaxCatsErrorViewModel;
import interface_adapter.runawaycat.RunawayCatViewModel;
import interface_adapter.setupsession.SetupSessionViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.timer.TimerViewModel;
import interface_adapter.display_cat_stats.DisplayCatStatsViewModel;
import view.AdoptionView;
import view.DisplayCatImageView;
import view.InventoryView;
import view.LoggedInView;
import view.LoginView;
import view.MaxCatsErrorView;
import view.RunawayCatView;
import view.SetupSessionView;
import view.SignupView;
import view.DisplayCatStatsView;

/**
 * Factory for creating views.
 */
public interface ViewFactory {
    /**
     * Creates a login view.
     * 
     * @param loginViewModel the view model
     * @return the view
     */
    LoginView createLoginView(LoginViewModel loginViewModel);

    /**
     * Creates a signup view.
     * 
     * @param signupViewModel the view model
     * @return the view
     */
    SignupView createSignupView(SignupViewModel signupViewModel);

    /**
     * Creates a logged in view.
     * 
     * @param loggedInViewModel the logged in view model
     * @param timerViewModel the timer view model
     * @param catViewModel the cat view model
     * @return the view
     */
    LoggedInView createLoggedInView(LoggedInViewModel loggedInViewModel,
            TimerViewModel timerViewModel,
            CatViewModel catViewModel);

    /**
     * Creates an adoption view.
     * 
     * @param adoptionViewModel the view model
     * @return the view
     */
    AdoptionView createAdoptionView(AdoptionViewModel adoptionViewModel);

    /**
     * Creates a runaway cat view.
     * 
     * @param runawayCatViewModel the view model
     * @return the view
     */
    RunawayCatView createRunawayCatView(RunawayCatViewModel runawayCatViewModel);

    /**
     * Creates a max cats error view.
     * 
     * @param maxCatsErrorViewModel the view model
     * @return the view
     */
    MaxCatsErrorView createMaxCatsErrorView(MaxCatsErrorViewModel maxCatsErrorViewModel);

    /**
     * Creates a display cat image view.
     * 
     * @param displayCatImageViewModel the view model
     * @return the view
     */
    DisplayCatImageView createDisplayCatImageView(DisplayCatImageViewModel displayCatImageViewModel);

    /**
     * Creates a setup session view.
     * 
     * @param setupSessionViewModel the view model
     * @return the view
     */
    SetupSessionView createSetupSessionView(SetupSessionViewModel setupSessionViewModel);

    /**
     * Creates an inventory view.
     * 
     * @param inventoryViewModel the view model
     * @return the view
     */
    InventoryView createInventoryView(InventoryViewModel inventoryViewModel);

    /**
     * Creates a new display cat stats view.
     *
     * @param viewModel the view model
     * @return the display cat stats view
     */
    DisplayCatStatsView createDisplayCatStatsView(DisplayCatStatsViewModel viewModel);
}
