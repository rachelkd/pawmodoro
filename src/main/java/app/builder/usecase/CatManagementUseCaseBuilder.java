package app.builder.usecase;

import app.builder.view.Views;
import app.components.DataAccessComponents;
import interface_adapter.adoption.AdoptionController;
import interface_adapter.adoption.AdoptionPresenter;
import interface_adapter.maxcatserror.MaxCatsErrorController;
import interface_adapter.maxcatserror.MaxCatsErrorPresenter;
import interface_adapter.runawaycat.RunawayCatController;
import interface_adapter.runawaycat.RunawayPresenter;
import use_case.adoption.AdoptionInputBoundary;
import use_case.adoption.AdoptionInteractor;
import use_case.adoption.AdoptionOutputBoundary;
import use_case.maxcatserror.MaxCatsErrorInputBoundary;
import use_case.maxcatserror.MaxCatsErrorInteractor;
import use_case.maxcatserror.MaxCatsErrorOutputBoundary;
import use_case.runawaycat.RunawayCatInputBoundary;
import use_case.runawaycat.RunawayCatInteractor;
import use_case.runawaycat.RunawayCatOutputBoundary;

/**
 * Builder for cat management-related use cases.
 */
public class CatManagementUseCaseBuilder extends AbstractUseCaseBuilder {
    /**
     * Creates a new cat management use case builder.
     *
     * @param views the views
     * @param dataAccess the data access components
     */
    public CatManagementUseCaseBuilder(Views views, DataAccessComponents dataAccess) {
        super(views, dataAccess);
    }

    /**
     * Builds the adoption use case.
     *
     * @return this builder
     */
    public CatManagementUseCaseBuilder buildAdoptionUseCase() {
        final AdoptionOutputBoundary outputBoundary = new AdoptionPresenter(
                getViews().getSession().getViewModels().getSetupSessionViewModel(),
                getViews().getCat().getViewModels().getAdoptionViewModel(),
                getViews().getViewManagerModel());

        final AdoptionInputBoundary interactor = new AdoptionInteractor(outputBoundary);

        final AdoptionController controller = new AdoptionController(interactor);
        getViews().getCat().getViews().getAdoptionView().setAdoptionController(controller);
        return this;
    }

    /**
     * Builds the max cats error use case.
     *
     * @return this builder
     */
    public CatManagementUseCaseBuilder buildMaxCatsErrorUseCase() {
        final MaxCatsErrorOutputBoundary outputBoundary = new MaxCatsErrorPresenter(
                getViews().getViewManagerModel(),
                getViews().getCat().getViewModels().getMaxCatsErrorViewModel());

        final MaxCatsErrorInputBoundary interactor = new MaxCatsErrorInteractor(outputBoundary);
        final MaxCatsErrorController controller = new MaxCatsErrorController(interactor);
        getViews().getCat().getViews().getMaxCatsErrorView().setMaxCatsController(controller);
        return this;
    }

    /**
     * Builds the runaway cat use case.
     *
     * @return this builder
     */
    public CatManagementUseCaseBuilder buildRunawayCatUseCase() {
        final RunawayCatOutputBoundary outputBoundary = new RunawayPresenter(
                getViews().getCat().getViewModels().getRunawayCatViewModel(),
                getViews().getViewManagerModel());

        final RunawayCatInputBoundary interactor = new RunawayCatInteractor(outputBoundary);
        final RunawayCatController controller =
                new RunawayCatController(getViews().getCat().getViewModels().getRunawayCatViewModel(), interactor);
        getViews().getCat().getViews().getRunawayCatView().setRunawayCatController(controller);

        return this;
    }

    /**
     * Builds all cat management-related use cases.
     *
     * @return this builder
     */
    public CatManagementUseCaseBuilder build() {
        return this
                .buildAdoptionUseCase()
                .buildMaxCatsErrorUseCase()
                .buildRunawayCatUseCase();
    }
}
