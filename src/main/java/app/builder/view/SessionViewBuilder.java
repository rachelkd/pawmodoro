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
import view.StudySessionView;
import view.TimerView;

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
    private StudySessionView studySessionView;
    private TimerView timerView;

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
        this.inventoryView = viewFactory.createInventoryView(viewModels.getInventoryViewModel());
        cardPanel.add(inventoryView, inventoryView.getViewName());
        return this;
    }

    /**
     * Builds the timer view.
     *
     * @return this builder
     */
    public SessionViewBuilder buildTimerView() {
        this.timerView = viewFactory.createTimerView(viewModels.getTimerViewModel());
        cardPanel.add(timerView, timerView.getViewName());
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
                studySessionView,
                timerView);

        return new SessionViewsAndModels(views, viewModels);
    }
}
