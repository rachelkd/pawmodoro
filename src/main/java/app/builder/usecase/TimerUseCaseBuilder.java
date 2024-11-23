package app.builder.usecase;

import app.builder.view.Views;
import app.components.DataAccessComponents;
import interface_adapter.setupsession.SetupSessionController;
import interface_adapter.setupsession.SetupSessionPresenter;
import interface_adapter.timer.TimerController;
import interface_adapter.timer.TimerPresenter;
import use_case.setupsession.SetupSessionInputBoundary;
import use_case.setupsession.SetupSessionInteractor;
import use_case.setupsession.SetupSessionOutputBoundary;

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
                getViews().getSession().getViewModels().getStudySessionViewModel());

        final SetupSessionInputBoundary interactor = new SetupSessionInteractor(outputBoundary);
        final SetupSessionController controller = new SetupSessionController(interactor);
        getViews().getSession().getViews().getSetupSessionView().setSetupSessionController(controller);
        return this;
    }

    /**
     * Builds all timer-related use cases.
     *
     * @return this builder
     */
    public TimerUseCaseBuilder build() {
        return this
                .buildSetupSessionUseCase();
    }
}
