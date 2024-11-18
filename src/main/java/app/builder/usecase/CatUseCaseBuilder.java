package app.builder.usecase;

import app.builder.view.Views;
import app.components.DataAccessComponents;
import interface_adapter.adoption.AdoptionController;
import interface_adapter.adoption.AdoptionPresenter;
import interface_adapter.display_cat_image.DisplayCatImageController;
import interface_adapter.display_cat_image.DisplayCatImagePresenter;
import interface_adapter.maxcatserror.MaxCatsErrorController;
import interface_adapter.maxcatserror.MaxCatsErrorPresenter;
import interface_adapter.runawaycat.RunawayCatController;
import interface_adapter.runawaycat.RunawayPresenter;
import use_case.adoption.AdoptionInputBoundary;
import use_case.adoption.AdoptionInteractor;
import use_case.adoption.AdoptionOutputBoundary;
import use_case.display_cat_image.DisplayCatImageInputBoundary;
import use_case.display_cat_image.DisplayCatImageInteractor;
import use_case.display_cat_image.DisplayCatImageOutputBoundary;
import use_case.maxcatserror.MaxCatsErrorInputBoundary;
import use_case.maxcatserror.MaxCatsErrorInteractor;
import use_case.maxcatserror.MaxCatsErrorOutputBoundary;
import use_case.runawaycat.RunawayCatOutputBoundary;

/**
 * Builder for cat-related use cases.
 */
public class CatUseCaseBuilder extends AbstractUseCaseBuilder {

    public CatUseCaseBuilder(Views views, DataAccessComponents dataAccess) {
        super(views, dataAccess);
    }

    public CatUseCaseBuilder buildAdoptionUseCase() {
        final AdoptionOutputBoundary outputBoundary = new AdoptionPresenter(
                getViews().getSession().getViewModels().getSetupSessionViewModel(),
                getViews().getCat().getViewModels().getAdoptionViewModel(),
                getViews().getViewManagerModel());

        final AdoptionInputBoundary interactor = new AdoptionInteractor(
                getDataAccess().getAdoptionDataAccess(),
                outputBoundary);

        final AdoptionController controller = new AdoptionController(interactor);
        getViews().getCat().getViews().getAdoptionView().setAdoptionController(controller);
        return this;
    }

    public CatUseCaseBuilder buildDisplayCatImageUseCase() {
        final DisplayCatImageOutputBoundary outputBoundary = new DisplayCatImagePresenter(
                getViews().getCat().getViewModels().getDisplayCatImageViewModel());

        final DisplayCatImageInputBoundary interactor = new DisplayCatImageInteractor(
                getDataAccess().getDisplayCatImageDataAccess(),
                outputBoundary);

        final DisplayCatImageController controller = new DisplayCatImageController(interactor);
        getViews().getCat().getViews().getDisplayCatImageView().setDisplayCatImageController(controller);
        return this;
    }

    public CatUseCaseBuilder buildMaxCatsErrorUseCase() {
        final MaxCatsErrorOutputBoundary outputBoundary = new MaxCatsErrorPresenter(
                getViews().getViewManagerModel(),
                getViews().getCat().getViewModels().getMaxCatsErrorViewModel());

        final MaxCatsErrorInputBoundary interactor = new MaxCatsErrorInteractor(outputBoundary);
        final MaxCatsErrorController controller = new MaxCatsErrorController(interactor);
        getViews().getCat().getViews().getMaxCatsErrorView().setMaxCatsController(controller);
        return this;
    }

    public CatUseCaseBuilder buildRunawayCatUseCase() {
        final RunawayCatOutputBoundary outputBoundary = new RunawayPresenter(
                getViews().getCat().getViewModels().getRunawayCatViewModel(),
                getViews().getViewManagerModel());

        final RunawayCatController controller = new RunawayCatController(
                getViews().getCat().getViewModels().getRunawayCatViewModel());
        getViews().getCat().getViews().getRunawayCatView().setRunawayCatController(controller);
        return this;
    }

    public CatUseCaseBuilder buildCatUseCases() {
        return this
                .buildAdoptionUseCase()
                .buildDisplayCatImageUseCase()
                .buildMaxCatsErrorUseCase()
                .buildRunawayCatUseCase();
    }
}
