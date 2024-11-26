package app.builder.view;

import java.awt.CardLayout;

import javax.swing.*;

import app.builder.view.cat.CatViewsAndModels;
import app.builder.view.session.SessionViewModels;
import app.builder.view.session.SessionViews;
import app.builder.view.session.SessionViewsAndModels;
import app.factory.ViewFactory;
import app.factory.viewmodel.SessionViewModelFactory;
import app.service.DialogService;
import interface_adapter.ViewManagerModel;
import view.*;

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
    private final CatViewsAndModels catViewsAndModels;

    // Views
    private BreakSessionView breakSessionView;
    private SetupSessionView setupSessionView;
    private InventoryView inventoryView;
    private StudySessionView studySessionView;
    private GetCatFactView getCatFactView;
    private DisplayCatStatsView displayCatStatsView;

    /**
     * Creates a new session view builder.
     * @param cardPanel the card panel
     * @param cardLayout the card layout
     * @param viewManagerModel the view manager model
     * @param viewFactory the view factory
     * @param dialogService the dialog service
     * @param catViewsAndModels the cat views and models
     */
    public SessionViewBuilder(JPanel cardPanel,
            CardLayout cardLayout,
            ViewManagerModel viewManagerModel,
            ViewFactory viewFactory,
            DialogService dialogService,
            CatViewsAndModels catViewsAndModels) {
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.viewManagerModel = viewManagerModel;
        this.viewFactory = viewFactory;
        this.sessionViewModelFactory = new SessionViewModelFactory();
        this.viewModels = new SessionViewModels(
                sessionViewModelFactory.createSetupSessionViewModel(),
                sessionViewModelFactory.createInventoryViewModel(),
                sessionViewModelFactory.createTimerViewModel(),
                sessionViewModelFactory.createStudySessionViewModel(),
                sessionViewModelFactory.createBreakSessionViewModel(),
                sessionViewModelFactory.createLoginViewModel(),
                sessionViewModelFactory.createGetCatFactViewModel(),
                sessionViewModelFactory.createDisplayCatStatsViewModel());
        this.dialogService = dialogService;
        this.catViewsAndModels = catViewsAndModels;
    }

    /**
     * Builds the setup session view.
     * @return this builder
     */
    public SessionViewBuilder buildSetupSessionView() {
        this.setupSessionView = viewFactory.createSetupSessionView(viewModels.getSetupSessionViewModel());
        cardPanel.add(setupSessionView, setupSessionView.getViewName());
        return this;
    }

    /**
     * Builds the study session view.
     * @return this builder
     */
    public SessionViewBuilder buildStudySessionView() {
        this.studySessionView = viewFactory.createStudySessionView(
                viewModels.getStudySessionViewModel(),
                viewModels.getDisplayCatStatsViewModel(),
                dialogService,
                catViewsAndModels.getViews().getCatView());
        cardPanel.add(studySessionView, studySessionView.getViewName());
        return this;
    }

    public SessionViewBuilder buildBreakSessionView() {
        this.breakSessionView = viewFactory.createBreakSessionView(
                viewModels.getBreakSessionViewModel());
        cardPanel.add(breakSessionView, breakSessionView.getViewName());
        return this;
    }

    /**
     * Builds the inventory view.
     * @return this builder
     */
    public SessionViewBuilder buildInventoryView() {
        // have inventory run in background
        inventoryView = viewFactory.createInventoryView(
                viewModels.getInventoryViewModel(),
                dialogService);
        return this;
    }

    /**
     * Builds the get cat fact view.
     * @return this builder
     */
    public SessionViewBuilder buildGetCatFactView() {
        this.getCatFactView = viewFactory.createGetCatFactView(viewModels.getGetCatFactViewModel());
        cardPanel.add(getCatFactView, getCatFactView.getViewName());
        return this;
    }

    public SessionViewBuilder buildDisplayCatStatsView() {
        // created when requested by user
        return this;
    }

    /**
     * Builds and returns the session views and models.
     * @return the session views and models
     */
    public SessionViewsAndModels build() {
        this.buildSetupSessionView()
                .buildInventoryView()
                .buildStudySessionView()
                .buildBreakSessionView()
                .buildGetCatFactView()
                .buildDisplayCatStatsView();

        final SessionViews views = new SessionViews(
                setupSessionView,
                inventoryView,
                studySessionView,
                breakSessionView,
                getCatFactView,
                displayCatStatsView);

        return new SessionViewsAndModels(views, viewModels);
    }
}
