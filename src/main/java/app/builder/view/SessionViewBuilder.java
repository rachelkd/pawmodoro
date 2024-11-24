package app.builder.view;

import java.awt.CardLayout;

import javax.swing.*;

import app.builder.view.session.SessionViewModels;
import app.builder.view.session.SessionViews;
import app.builder.view.session.SessionViewsAndModels;
import app.factory.ViewFactory;
import app.factory.viewmodel.SessionViewModelFactory;
import app.service.DialogService;
import interface_adapter.ViewManagerModel;
import view.InventoryView;
import view.SetupSessionView;
import view.StudySessionView;

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
    private final DialogService dialogService;


    // Views
    private SetupSessionView setupSessionView;
    private InventoryView inventoryView;
    private StudySessionView studySessionView;

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
        this.dialogService = new DialogService();
        this.viewModels = new SessionViewModels(
                sessionViewModelFactory.createSetupSessionViewModel(),
                sessionViewModelFactory.createInventoryViewModel(),
                sessionViewModelFactory.createTimerViewModel(),
                sessionViewModelFactory.createStudySessionViewModel());
    }

    /**
     * Builds the setup session view.
     *
     * @return this builder
     */
    public SessionViewBuilder buildSetupSessionView() {
        this.setupSessionView = viewFactory.createSetupSessionView(viewModels.getSetupSessionViewModel());
        cardPanel.add(setupSessionView, setupSessionView.getViewName());
        return this;
    }

    /**
     * Builds the study session view.
     *
     * @return this builder
     */
    public SessionViewBuilder buildStudySessionView() {
        this.studySessionView = viewFactory.createStudySessionView(viewModels.getStudySessionViewModel(),
                viewModels.getTimerViewModel());
        cardPanel.add(studySessionView, studySessionView.getViewName());
        return this;
    }

    /**
     * Builds the inventory view.
     *
     * @return this builder
     */
    public SessionViewBuilder buildInventoryView() {
        // have inventory run in background
        inventoryView = viewFactory
                .createInventoryView((JFrame) cardPanel.getParent(), viewModels.getInventoryViewModel(), dialogService);
        // cardPanel.add(inventoryView, inventoryView.getViewName()); // find way to get inventory from here and set to visible
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
                inventoryView,
                studySessionView);

        return new SessionViewsAndModels(views, viewModels);
    }
}
