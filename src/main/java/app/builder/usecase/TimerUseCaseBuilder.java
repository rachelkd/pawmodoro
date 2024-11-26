package app.builder.usecase;

import app.builder.view.Views;
import app.components.DataAccessComponents;
import interface_adapter.break_session.BreakSessionController;
import interface_adapter.break_session.BreakSessionPresenter;
import interface_adapter.setupsession.SetupSessionController;
import interface_adapter.setupsession.SetupSessionPresenter;
import interface_adapter.study_session.StudySessionController;
import interface_adapter.study_session.StudySessionPresenter;
import use_case.breaksession.BreakSessionInputBoundary;
import use_case.breaksession.BreakSessionInteractor;
import use_case.breaksession.BreakSessionOutputBoundary;
import use_case.setupsession.SetupSessionInputBoundary;
import use_case.setupsession.SetupSessionInteractor;
import use_case.setupsession.SetupSessionOutputBoundary;
import use_case.studysession.StudySessionInputBoundary;
import use_case.studysession.StudySessionInteractor;
import use_case.studysession.StudySessionOutputBoundary;

/**
 * Builder for timer-related use cases.
 */
public class TimerUseCaseBuilder extends AbstractUseCaseBuilder {

    public TimerUseCaseBuilder(Views views, DataAccessComponents dataAccess) {
        super(views, dataAccess);
    }

    // TODO: Move this into SessionsUseCaseBuilder?

    /**
     * Builds the setup session use case.
     *
     * @return this builder
     */
    public TimerUseCaseBuilder buildSetupSessionUseCase() {
        final SetupSessionOutputBoundary outputBoundary = new SetupSessionPresenter(
                getViews().getViewManagerModel(),
                getViews().getSession().getViewModels().getStudySessionViewModel(),
                getViews().getSession().getViewModels().getBreakSessionViewModel());

        final SetupSessionInputBoundary interactor = new SetupSessionInteractor(outputBoundary);
        final SetupSessionController controller = new SetupSessionController(interactor);
        getViews().getSession().getViews().getSetupSessionView().setSetupSessionController(controller);
        return this;
    }

    /**
     * Builds the study session use case.
     *
     * @return this builder
     */
    public TimerUseCaseBuilder buildStudySessionUseCase() {
        final StudySessionOutputBoundary studySessionOutputBoundary = new StudySessionPresenter(
                getViews().getViewManagerModel(),
                getViews().getSession().getViewModels().getLoginViewModel(),
                getViews().getSession().getViewModels().getSetupSessionViewModel(),
                getViews().getSession().getViewModels().getBreakSessionViewModel());

        final StudySessionInputBoundary studySessionInteractor = new StudySessionInteractor(studySessionOutputBoundary);
        final StudySessionController studySessionController = new StudySessionController(studySessionInteractor);
        getViews().getSession().getViews().getStudySessionView().setStudySessionController(studySessionController);
        return this;
    }

    /**
     * Builds the break session use case.
     *
     * @return this builder
     */
    public TimerUseCaseBuilder buildBreakSessionUseCase() {
        final BreakSessionOutputBoundary breakSessionOutputBoundary = new BreakSessionPresenter(
                getViews().getViewManagerModel(),
                getViews().getSession().getViewModels().getLoginViewModel(),
                getViews().getSession().getViewModels().getStudySessionViewModel());

        final BreakSessionInputBoundary breakSessionInteractor = new BreakSessionInteractor(breakSessionOutputBoundary);
        final BreakSessionController breakSessionController = new BreakSessionController(breakSessionInteractor);
        getViews().getSession().getViews().getBreakSessionView().setBreakSessionController(breakSessionController);
        return this;
    }

    /**
     * Builds all timer-related use cases.
     *
     * @return this builder
     */
    public TimerUseCaseBuilder build() {
        return this
                .buildSetupSessionUseCase()
                .buildStudySessionUseCase()
                .buildBreakSessionUseCase();
    }
}
