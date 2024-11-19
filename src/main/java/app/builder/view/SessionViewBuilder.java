package app.builder.view;

import java.awt.CardLayout;

import javax.swing.JPanel;

import app.builder.view.session.SessionViewModels;
import app.builder.view.session.SessionViews;
import app.builder.view.session.SessionViewsAndModels;
import app.factory.ViewFactory;
import app.factory.viewmodel.SessionViewModelFactory;
import interface_adapter.ViewManagerModel;
import view.InventoryView;
import view.SetupSessionView;

/**
 * Builder for session-related views.
 */
public class SessionViewBuilder {
    private final JPanel cardPanel;
    private final CardLayout cardLayout;
    private final ViewManagerModel viewManagerModel;
    private final ViewFactory viewFactory;
    private final SessionViewModelFactory sessionViewModelFactory;
    private final SessionViewModels viewModels;

    // Views
    private SetupSessionView setupSessionView;
    private InventoryView inventoryView;

    /**
     * Creates a new session view builder.
     *
     * @param cardPanel the card panel
     * @param cardLayout the card layout
     * @param viewManagerModel the view manager model
     * @param viewFactory the view factory
     */
    public SessionViewBuilder(JPanel cardPanel,
            CardLayout cardLayout,
            ViewManagerModel viewManagerModel,
            ViewFactory viewFactory) {
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.viewManagerModel = viewManagerModel;
        this.viewFactory = viewFactory;
        this.sessionViewModelFactory = new SessionViewModelFactory();
        this.viewModels = new SessionViewModels(
                sessionViewModelFactory.createSetupSessionViewModel(),
                sessionViewModelFactory.createInventoryViewModel(),
                sessionViewModelFactory.createTimerViewModel());
    }

    /**
     * Builds the setup session view.
     *
     * @return this builder for method chaining
     */
    public SessionViewBuilder buildSetupSessionView() {
        setupSessionView = viewFactory.createSetupSessionView(viewModels.getSetupSessionViewModel());
        cardPanel.add(setupSessionView, setupSessionView.getViewName());
        return this;
    }

    /**
     * Builds the inventory view.
     *
     * @return this builder for method chaining
     */
    public SessionViewBuilder buildInventoryView() {
        inventoryView = viewFactory.createInventoryView(viewModels.getInventoryViewModel());
        cardPanel.add(inventoryView, inventoryView.getViewName());
        return this;
    }

    /**
     * Builds and returns the session views and models.
     *
     * @return the session views and models
     */
    public SessionViewsAndModels build() {
        final SessionViews views = new SessionViews(
                setupSessionView,
                inventoryView);

        return new SessionViewsAndModels(views, viewModels);
    }
}
