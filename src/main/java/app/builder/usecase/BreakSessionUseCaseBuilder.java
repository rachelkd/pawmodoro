package app.builder.usecase;

import app.builder.view.Views;
import interface_adapter.break_session.BreakSessionController;
import interface_adapter.break_session.BreakSessionPresenter;
import use_case.breaksession.BreakSessionInputBoundary;
import use_case.breaksession.BreakSessionInteractor;
import use_case.breaksession.BreakSessionOutputBoundary;

/**
 * The usecase builder for break session.
 */
class BreakSessionUseCaseBuilder {
    private final Views views;

    BreakSessionUseCaseBuilder(Views views) {
        this.views = views;
    }

    void build() {
        final BreakSessionOutputBoundary outputBoundary = new BreakSessionPresenter(
                views.getViewManagerModel(),
                views.getSession().getViewModels().getLoginViewModel(),
                views.getSession().getViewModels().getStudySessionViewModel());

        final BreakSessionInputBoundary interactor = new BreakSessionInteractor(outputBoundary);
        final BreakSessionController controller = new BreakSessionController(interactor);
        views.getSession().getViews().getBreakSessionView().setBreakSessionController(controller);
    }
}
