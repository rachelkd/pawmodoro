package app.builder.usecase;

import app.builder.view.Views;
import app.components.DataAccessComponents;
import interface_adapter.timer.TimerController;
import interface_adapter.timer.TimerPresenter;
import interface_adapter.setupsession.SetupSessionController;
import interface_adapter.setupsession.SetupSessionPresenter;
import use_case.timer.display_timer.DisplayTimerInputBoundary;
import use_case.timer.display_timer.DisplayTimerInteractor;
import use_case.timer.display_timer.DisplayTimerOutputBoundary;
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

    /**
     * Builds the timer use case.
     *
     * @return this builder for method chaining
     */
    public TimerUseCaseBuilder buildTimerUseCase() {
        final DisplayTimerOutputBoundary outputBoundary = new TimerPresenter(
                getViews().getSession().getViewModels().getTimerViewModel());

        final DisplayTimerInputBoundary interactor = new DisplayTimerInteractor(
                getDataAccess().getTimerDataAccess(),
                outputBoundary);

        final TimerController controller = new TimerController(interactor);
        getViews().getAuth().getViews().getLoggedInView().setTimerController(controller);
        return this;
    }

    /**
     * Builds the setup session use case.
     *
     * @return this builder for method chaining
     */
    public TimerUseCaseBuilder buildSetupSessionUseCase() {
        final SetupSessionOutputBoundary outputBoundary = new SetupSessionPresenter(
                getViews().getSession().getViewModels().getSetupSessionViewModel(),
                getViews().getViewManagerModel(),
                getViews().getSession().getViewModels().getTimerViewModel());

        final SetupSessionInputBoundary interactor = new SetupSessionInteractor(outputBoundary);
        final SetupSessionController controller = new SetupSessionController(interactor);
        getViews().getSession().getViews().getSetupSessionView().setSetupSessionController(controller);
        return this;
    }

    /**
     * Builds all timer-related use cases.
     *
     * @return this builder for method chaining
     */
    public TimerUseCaseBuilder buildTimerUseCases() {
        return this
                .buildTimerUseCase()
                .buildSetupSessionUseCase();
    }
}
