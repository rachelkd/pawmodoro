package app.builder.usecase;

import app.builder.view.Views;
import interface_adapter.setupsession.SetupSessionController;
import interface_adapter.setupsession.SetupSessionPresenter;
import use_case.setupsession.SetupSessionInputBoundary;
import use_case.setupsession.SetupSessionInteractor;
import use_case.setupsession.SetupSessionOutputBoundary;

/**
 * The usecase builder for setup session.
 */
class SetupSessionUseCaseBuilder {
    private final Views views;

    SetupSessionUseCaseBuilder(Views views) {
        this.views = views;
    }

    void build() {
        final SetupSessionOutputBoundary outputBoundary = new SetupSessionPresenter(
                views.getViewManagerModel(),
                views.getSession().getViewModels().getStudySessionViewModel(),
                views.getSession().getViewModels().getBreakSessionViewModel());

        final SetupSessionInputBoundary interactor = new SetupSessionInteractor(outputBoundary);
        final SetupSessionController controller = new SetupSessionController(interactor);
        views.getSession().getViews().getSetupSessionView().setSetupSessionController(controller);
    }
}
