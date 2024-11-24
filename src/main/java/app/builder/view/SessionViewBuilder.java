package app.builder.view;

import java.awt.CardLayout;

import javax.swing.JPanel;

import app.builder.view.cat.CatViewModels;
import app.builder.view.cat.CatViewsAndModels;
import app.builder.view.session.SessionViewModels;
import app.builder.view.session.SessionViews;
import app.builder.view.session.SessionViewsAndModels;
import app.factory.ViewFactory;
import app.factory.viewmodel.SessionViewModelFactory;
import app.service.DialogService;
import interface_adapter.ViewManagerModel;
import interface_adapter.break_session.BreakSessionPresenter;
import interface_adapter.study_session.StudySessionController;
import interface_adapter.study_session.StudySessionPresenter;
import use_case.studysession.StudySessionInputBoundary;
import use_case.studysession.StudySessionInteractor;
import use_case.studysession.StudySessionOutputBoundary;
import use_case.breaksession.BreakSessionOutputBoundary;
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
    private final CatViewsAndModels catViewsAndModels;

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
                sessionViewModelFactory.createBreakSessionViewModel());
        this.dialogService = dialogService;
        this.catViewsAndModels = catViewsAndModels;
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
        final StudySessionOutputBoundary presenter = new StudySessionPresenter(
                viewManagerModel,
                sessionViewModelFactory.createLoginViewModel(),
                viewModels.getSetupSessionViewModel(),
                viewModels.getBreakSessionViewModel());

        final StudySessionInputBoundary interactor = new StudySessionInteractor(presenter);
        final StudySessionController studySessionController = new StudySessionController(interactor);

        this.studySessionView = viewFactory.createStudySessionView(
                viewModels.getStudySessionViewModel(),
                catViewsAndModels.getViewModels().getCatViewModel(),
                catViewsAndModels.getViewModels().getDisplayCatStatsViewModel(),
                dialogService,
                catViewsAndModels.getViews().getCatView(),
                studySessionController,
                presenter);
        cardPanel.add(studySessionView, studySessionView.getViewName());
        return this;
    }

    public SessionViewBuilder buildBreakSessionView() {
        final BreakSessionOutputBoundary presenter = new BreakSessionPresenter(
                viewManagerModel,
                viewModels.getLoginViewModel(),
                viewModels.getBreakSessionViewModel());

        this.breakSessionView = viewFactory.createBreakSessionView(viewModels.getBreakSessionViewModel());
        cardPanel.add(breakSessionView, breakSessionView.getViewName());
        return this;
    }

    /**
     * Builds the inventory view.
     *
     * @return this builder
     */
    public SessionViewBuilder buildInventoryView() {
        this.inventoryView = viewFactory.createInventoryView(viewModels.getInventoryViewModel());
        cardPanel.add(inventoryView, inventoryView.getViewName());
        return this;
    }

    /**
     * Builds and returns the session views and models.
     *
     * @return the session views and models
     */
    public SessionViewsAndModels build() {
        this.buildSetupSessionView()
                .buildInventoryView()
                .buildStudySessionView();

        final SessionViews views = new SessionViews(
                setupSessionView,
                inventoryView,
                studySessionView);

        return new SessionViewsAndModels(views, viewModels);
    }
}
