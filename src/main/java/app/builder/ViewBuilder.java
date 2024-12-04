package app.builder;

import java.awt.CardLayout;

import javax.swing.JPanel;

import app.builder.view.AuthViewBuilder;
import app.builder.view.CatViewBuilder;
import app.builder.view.SessionViewBuilder;
import app.builder.view.SharedViewBuilder;
import app.builder.view.Views;
import app.builder.view.auth.AuthViewsAndModels;
import app.builder.view.cat.CatViewsAndModels;
import app.builder.view.session.SessionViewsAndModels;
import app.builder.view.shared.SharedViewsAndModels;
import app.factory.ViewFactory;
import app.service.DialogService;
import interface_adapter.ViewManagerModel;

/**
 * Main builder that coordinates all view builders.
 */
public class ViewBuilder {
    private final JPanel cardPanel;
    private final CardLayout cardLayout;
    private final ViewManagerModel viewManagerModel;
    private final ViewFactory viewFactory;
    private final DialogService dialogService;

    private SharedViewsAndModels sharedViewsAndModels;
    private CatViewsAndModels catViewsAndModels;
    private SessionViewsAndModels sessionViewsAndModels;
    private AuthViewsAndModels authViewsAndModels;

    /**
     * Creates a new view builder.
     * @param cardPanel the card panel
     * @param cardLayout the card layout
     * @param viewManagerModel the view manager model
     * @param viewFactory the view factory
     * @param dialogService the dialog service
     */
    public ViewBuilder(JPanel cardPanel, CardLayout cardLayout, ViewManagerModel viewManagerModel, 
                       ViewFactory viewFactory, DialogService dialogService) {
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.viewManagerModel = viewManagerModel;
        this.viewFactory = viewFactory;
        this.dialogService = dialogService;
    }

    /**
     * Builds and returns all views.
     * @return the views
     */
    public Views build() {
        return this
                .buildSharedViews()
                .buildCatViews()
                .buildSessionViews()
                .buildAuthViews()
                .createViews();
    }

    /**
     * Builds shared views and models.
     * @return this builder
     */
    private ViewBuilder buildSharedViews() {
        final SharedViewBuilder sharedViewBuilder = new SharedViewBuilder(
                cardPanel, cardLayout, viewManagerModel, viewFactory, dialogService);
        this.sharedViewsAndModels = sharedViewBuilder.build();
        return this;
    }

    /**
     * Builds cat-specific views and models.
     * @return this builder
     */
    private ViewBuilder buildCatViews() {
        final CatViewBuilder catViewBuilder =
                new CatViewBuilder(cardPanel, cardLayout, viewManagerModel, viewFactory,
                        dialogService);
        this.catViewsAndModels = catViewBuilder.build();
        return this;
    }

    /**
     * Builds session views and models.
     * @return this builder
     */
    private ViewBuilder buildSessionViews() {
        final SessionViewBuilder sessionViewBuilder =
                new SessionViewBuilder(cardPanel, cardLayout, viewManagerModel, viewFactory,
                        dialogService, sharedViewsAndModels, catViewsAndModels);
        this.sessionViewsAndModels = sessionViewBuilder.build();
        return this;
    }

    /**
     * Builds auth views and models.
     * @return this builder
     */
    private ViewBuilder buildAuthViews() {
        final AuthViewBuilder authViewBuilder =
                new AuthViewBuilder(cardPanel, cardLayout, viewManagerModel, viewFactory,
                        sessionViewsAndModels.getViewModels(), catViewsAndModels.getViewModels());
        this.authViewsAndModels = authViewBuilder.build();
        return this;
    }

    /**
     * Creates and returns the views container.
     * @return the views
     */
    private Views createViews() {
        return new Views(authViewsAndModels, catViewsAndModels,
                sessionViewsAndModels, sharedViewsAndModels, viewManagerModel);
    }
}
