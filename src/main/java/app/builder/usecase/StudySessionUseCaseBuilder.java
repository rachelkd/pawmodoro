package app.builder.usecase;

import app.builder.view.Views;
import interface_adapter.study_session.StudySessionController;
import interface_adapter.study_session.StudySessionPresenter;
import use_case.studysession.StudySessionInputBoundary;
import use_case.studysession.StudySessionInteractor;
import use_case.studysession.StudySessionOutputBoundary;

/**
 * The Usecase builder for study session.
 */
class StudySessionUseCaseBuilder {
    private final Views views;

    StudySessionUseCaseBuilder(Views views) {
        this.views = views;
    }

    void build() {
        final StudySessionOutputBoundary outputBoundary = new StudySessionPresenter(
                views.getViewManagerModel(),
                views.getSession().getViewModels().getLoginViewModel(),
                views.getSession().getViewModels().getSetupSessionViewModel(),
                views.getSession().getViewModels().getBreakSessionViewModel(),
                views.getSession().getViewModels().getStudySessionViewModel());

        final StudySessionInputBoundary interactor = new StudySessionInteractor(outputBoundary);
        final StudySessionController controller = new StudySessionController(interactor);
        views.getSession().getViews().getStudySessionView().setStudySessionController(controller);
    }
}
