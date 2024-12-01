package app.builder.view;

import java.awt.CardLayout;

import javax.swing.JPanel;

import app.builder.view.session.SessionViewModels;
import app.builder.view.session.SessionViews;
import app.builder.view.session.SessionViewsAndModels;
import app.builder.view.shared.SharedViewsAndModels;
import app.factory.ViewFactory;
import app.factory.viewmodel.SessionViewModelFactory;
import app.service.DialogService;
import interface_adapter.ViewManagerModel;
import view.BreakSessionView;
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

    private final SharedViewsAndModels sharedViewsAndModels;

    // Views
    private BreakSessionView breakSessionView;
    private SetupSessionView setupSessionView;
    private StudySessionView studySessionView;

    /**
     * Creates a new session view builder.
     * @param cardPanel the card panel
     * @param cardLayout the card layout
     * @param viewManagerModel the view manager model
     * @param viewFactory the view factory
     * @param dialogService the dialog service
     * @param sharedViewsAndModels the shared views and models
     */
    public SessionViewBuilder(JPanel cardPanel,
            CardLayout cardLayout,
            ViewManagerModel viewManagerModel,
            ViewFactory viewFactory,
            DialogService dialogService,
            SharedViewsAndModels sharedViewsAndModels) {
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.viewManagerModel = viewManagerModel;
        this.viewFactory = viewFactory;
        this.sessionViewModelFactory = new SessionViewModelFactory();
        this.viewModels = new SessionViewModels(
                sessionViewModelFactory.createSetupSessionViewModel(),
                sessionViewModelFactory.createTimerViewModel(),
                sessionViewModelFactory.createStudySessionViewModel(),
                sessionViewModelFactory.createBreakSessionViewModel(),
                sessionViewModelFactory.createLoginViewModel());
        this.dialogService = dialogService;
        this.sharedViewsAndModels = sharedViewsAndModels;
    }

    /**
     * Builds and returns the session views and models.
     * @return the session views and models
     */
    public SessionViewsAndModels build() {
        this
                .buildSetupSessionView()
                .buildBreakSessionView()
                .buildStudySessionView();
        // build break session first so cat container show up in study session view first, not break session

        breakSessionView.setStudySessionView(studySessionView);
        studySessionView.setBreakSessionView(breakSessionView);

        final SessionViews views = new SessionViews(
                setupSessionView,
                studySessionView,
                breakSessionView);

        return new SessionViewsAndModels(views, viewModels);
    }

    /**
     * Builds the setup session view.
     * @return this builder
     */
    private SessionViewBuilder buildSetupSessionView() {
        this.setupSessionView = viewFactory.createSetupSessionView(
                viewModels.getSetupSessionViewModel());
        cardPanel.add(setupSessionView, setupSessionView.getViewName());
        return this;
    }

    /**
     * Builds the study session view.
     * @return this builder
     */
    private SessionViewBuilder buildStudySessionView() {
        this.studySessionView = viewFactory.createStudySessionView(
                viewModels.getStudySessionViewModel(),
                viewModels.getBreakSessionViewModel(),
                sharedViewsAndModels.getViewModels().getInitializeCatsViewModel(),
                dialogService,
                sharedViewsAndModels.getViews().getCatContainerView());
        cardPanel.add(studySessionView, studySessionView.getViewName());
        return this;
    }

    /**
     * Builds the break session view.
     * @return this builder
     */
    private SessionViewBuilder buildBreakSessionView() {
        this.breakSessionView = viewFactory.createBreakSessionView(
                viewModels.getBreakSessionViewModel(),
                // viewModels.getBreakSessionViewModel().getState(),
                sharedViewsAndModels.getViewModels().getAdoptionViewModel(),
                dialogService,
                sharedViewsAndModels.getViews().getCatContainerView());
        cardPanel.add(breakSessionView, breakSessionView.getViewName());
        return this;
    }
}
