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

    /**
     * Creates a new view builder.
     *
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
     *
     * @return the views
     */
    public Views build() {
        final CatViewBuilder catViewBuilder =
                new CatViewBuilder(cardPanel, cardLayout, viewManagerModel, viewFactory, dialogService);

        final CatViewsAndModels catViewsAndModels = catViewBuilder.build();

        final SessionViewBuilder sessionViewBuilder =
                new SessionViewBuilder(cardPanel, cardLayout, viewManagerModel, viewFactory,
                        dialogService, catViewsAndModels.getViewModels());

        // TODO: Refactor DisplayCatImageView to be in Session category?

        final SessionViewsAndModels sessionViewsAndModels = sessionViewBuilder.build();

        final AuthViewBuilder authViewBuilder =
                new AuthViewBuilder(cardPanel, cardLayout, viewManagerModel, viewFactory,
                        sessionViewsAndModels.getViewModels(), catViewsAndModels.getViewModels());

        final AuthViewsAndModels authViewsAndModels = authViewBuilder.build();

        return new Views(authViewsAndModels, catViewsAndModels, sessionViewsAndModels, viewManagerModel);
    }
}
