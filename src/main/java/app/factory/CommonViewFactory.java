package app.factory;

// import interface_adapter.ViewManagerModel;
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
import interface_adapter.timer.TimerViewModel;
import view.*;
import javax.swing.JFrame;

import app.service.DialogService;

/**
 * Default implementation of ViewFactory.
 */
public class CommonViewFactory implements ViewFactory {
    // private final ViewManagerModel viewManagerModel;

    // public CommonViewFactory(ViewManagerModel viewManagerModel) {
    // this.viewManagerModel = viewManagerModel;
    // }

    @Override
    public LoginView createLoginView(LoginViewModel loginViewModel) {
        return new LoginView(loginViewModel);
    }

    @Override
    public SignupView createSignupView(SignupViewModel signupViewModel) {
        return new SignupView(signupViewModel);
    }

    @Override
    public LoggedInView createLoggedInView(LoggedInViewModel loggedInViewModel,
            TimerViewModel timerViewModel,
            CatViewModel catViewModel,
            DisplayCatStatsViewModel displayCatStatsViewModel) {
        return new LoggedInView(loggedInViewModel, timerViewModel, catViewModel, displayCatStatsViewModel);
    }

    @Override
    public AdoptionView createAdoptionView(AdoptionViewModel adoptionViewModel) {
        return new AdoptionView(adoptionViewModel);
    }

    @Override
    public RunawayCatView createRunawayCatView(RunawayCatViewModel runawayCatViewModel) {
        return new RunawayCatView(runawayCatViewModel);
    }

    @Override
    public MaxCatsErrorView createMaxCatsErrorView(MaxCatsErrorViewModel maxCatsErrorViewModel) {
        return new MaxCatsErrorView(maxCatsErrorViewModel);
    }

    @Override
    public DisplayCatImageView createDisplayCatImageView(DisplayCatImageViewModel displayCatImageViewModel) {
        return new DisplayCatImageView(displayCatImageViewModel);
    }

    @Override
    public SetupSessionView createSetupSessionView(SetupSessionViewModel setupSessionViewModel) {
        return new SetupSessionView(setupSessionViewModel);
    }

    @Override
    public InventoryView createInventoryView(InventoryViewModel inventoryViewModel) {
        return new InventoryView(inventoryViewModel);
    }

    @Override
    public CatView createCatView(CatViewModel catViewModel,
            DisplayCatStatsViewModel displayCatStatsViewModel,
            DialogService dialogService) {
        return new CatView(catViewModel, displayCatStatsViewModel, dialogService);
    }
}
