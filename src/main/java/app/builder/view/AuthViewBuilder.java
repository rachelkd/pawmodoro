package app.builder.view;

import java.awt.CardLayout;

import javax.swing.JPanel;

import app.builder.view.auth.AuthViewModels;
import app.builder.view.auth.AuthViews;
import app.builder.view.auth.AuthViewsAndModels;
import app.builder.view.cat.CatViewModels;
import app.builder.view.session.SessionViewModels;
import app.factory.ViewFactory;
import app.factory.viewmodel.AuthViewModelFactory;
import interface_adapter.ViewManagerModel;
import view.LoggedInView;
import view.LoginView;
import view.SignupView;

/**
 * Builder for authentication-related views.
 */
public class AuthViewBuilder {
    private final JPanel cardPanel;
    private final CardLayout cardLayout;
    private final ViewManagerModel viewManagerModel;
    private final ViewFactory viewFactory;
    private final AuthViewModelFactory authViewModelFactory;
    private final AuthViewModels authViewModels;
    private final SessionViewModels sessionViewModels;
    private final CatViewModels catViewModels;

    // Views
    private LoginView loginView;
    private SignupView signupView;
    private LoggedInView loggedInView;

    public AuthViewBuilder(JPanel cardPanel,
            CardLayout cardLayout,
            ViewManagerModel viewManagerModel,
            ViewFactory viewFactory,
            SessionViewModels sessionViewModels,
            CatViewModels catViewModels) {
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.viewManagerModel = viewManagerModel;
        this.viewFactory = viewFactory;
        this.sessionViewModels = sessionViewModels;
        this.catViewModels = catViewModels;
        this.authViewModelFactory = new AuthViewModelFactory();
        this.authViewModels = new AuthViewModels(
                authViewModelFactory.createLoginViewModel(),
                authViewModelFactory.createSignupViewModel(),
                authViewModelFactory.createLoggedInViewModel());
    }

    /**
     * Builds the login view.
     * 
     * @return this builder
     */
    public AuthViewBuilder buildLoginView() {
        loginView = viewFactory.createLoginView(authViewModels.getLoginViewModel());
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Builds the signup view.
     * 
     * @return this builder
     */
    public AuthViewBuilder buildSignupView() {
        signupView = viewFactory.createSignupView(authViewModels.getSignupViewModel());
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Builds the logged-in view.
     * 
     * @return this builder
     */
    public AuthViewBuilder buildLoggedInView() {
        loggedInView = viewFactory.createLoggedInView(
                authViewModels.getLoggedInViewModel());
        cardPanel.add(loggedInView, loggedInView.getViewName());
        return this;
    }

    /**
     * Builds and returns all authentication views.
     * 
     * @return the authentication views and view models
     */
    public AuthViewsAndModels build() {
        final AuthViews views = new AuthViews(
                loginView,
                signupView,
                loggedInView);

        return new AuthViewsAndModels(views, authViewModels);
    }
}
