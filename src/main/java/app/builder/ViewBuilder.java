package app.builder;

import java.awt.CardLayout;

import javax.swing.JPanel;

import app.builder.view.AuthViewBuilder;
import app.builder.view.CatViewBuilder;
import app.builder.view.SessionViewBuilder;
import app.builder.view.Views;
import app.builder.view.auth.AuthViewsAndModels;
import app.builder.view.cat.CatViewsAndModels;
import app.builder.view.session.SessionViewsAndModels;
import app.factory.ViewFactory;
import interface_adapter.ViewManagerModel;

/**
 * Main builder that coordinates all view builders.
 */
public class ViewBuilder {
    private final JPanel cardPanel;
    private final CardLayout cardLayout;
    private final ViewManagerModel viewManagerModel;
    private final ViewFactory viewFactory;

    private final CatViewBuilder catViewBuilder;
    private final SessionViewBuilder sessionViewBuilder;

    /**
     * Creates a new view builder.
     *
     * @param cardPanel the card panel
     * @param cardLayout the card layout
     * @param viewManagerModel the view manager model
     * @param viewFactory the view factory
     */
    public ViewBuilder(JPanel cardPanel,
            CardLayout cardLayout,
            ViewManagerModel viewManagerModel,
            ViewFactory viewFactory) {
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.viewManagerModel = viewManagerModel;
        this.viewFactory = viewFactory;
        // TODO: Remove cardLayout and viewManagerModel from constructor?
        this.catViewBuilder = new CatViewBuilder(cardPanel, cardLayout, viewManagerModel, viewFactory);
        this.sessionViewBuilder = new SessionViewBuilder(cardPanel, cardLayout, viewManagerModel, viewFactory);
    }

    /**
     * Builds and returns all views.
     *
     * @return the views
     */
    public Views build() {
        // TODO: Refactor DisplayCatImageView to be in Session category?
        // TODO: Change all View Builder constructors to not take in cardLayout and viewManagerModel?
        final CatViewsAndModels catViewsAndModels = catViewBuilder
                .buildAdoptionView()
                .buildRunawayCatView()
                .buildMaxCatsErrorView()
                .buildDisplayCatImageView()
                .buildDisplayCatStatsView()
                .buildCatView()
                .build();

        final SessionViewsAndModels sessionViewsAndModels = sessionViewBuilder
                .buildSetupSessionView()
                .buildInventoryView()
                .build();

        final AuthViewsAndModels authViewsAndModels = new AuthViewBuilder(
                cardPanel,
                cardLayout,
                viewManagerModel,
                viewFactory,
                sessionViewsAndModels.getViewModels(),
                catViewsAndModels.getViewModels())
                        .buildLoginView()
                        .buildSignupView()
                        .buildLoggedInView()
                        .build();

        return new Views(authViewsAndModels, catViewsAndModels, sessionViewsAndModels, viewManagerModel);
    }
}
