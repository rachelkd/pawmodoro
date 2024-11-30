package app.builder.usecase;

import app.builder.view.Views;
import app.components.DataAccessComponents;
import interface_adapter.display_cat_image.DisplayCatImageController;
import interface_adapter.display_cat_image.DisplayCatImagePresenter;
import interface_adapter.initialize_cats.CatViewModelFactory;
import interface_adapter.initialize_cats.InitializeCatsController;
import interface_adapter.initialize_cats.InitializeCatsPresenter;
import use_case.cat_management.initialize_cats.InitializeCatsInputBoundary;
import use_case.cat_management.initialize_cats.InitializeCatsInteractor;
import use_case.cat_management.initialize_cats.InitializeCatsOutputBoundary;
import use_case.display_cat_image.DisplayCatImageInputBoundary;
import use_case.display_cat_image.DisplayCatImageInteractor;
import use_case.display_cat_image.DisplayCatImageOutputBoundary;

/**
 * Builder for cat display-related use cases.
 */
public class CatDisplayUseCaseBuilder extends AbstractUseCaseBuilder {
    /**
     * Creates a new cat display use case builder.
     * @param views the views
     * @param dataAccess the data access components
     */
    public CatDisplayUseCaseBuilder(Views views, DataAccessComponents dataAccess) {
        super(views, dataAccess);
    }

    /**
     * Builds the display cat image use case.
     * @return this builder
     */
    public CatDisplayUseCaseBuilder buildDisplayCatImageUseCase() {
        final DisplayCatImageOutputBoundary outputBoundary = new DisplayCatImagePresenter(
                getViews().getCat().getViewModels().getDisplayCatImageViewModel());

        final DisplayCatImageInputBoundary interactor = new DisplayCatImageInteractor(
                getDataAccess().getDisplayCatImageDataAccess(),
                outputBoundary);

        final DisplayCatImageController controller = new DisplayCatImageController(interactor);
        getViews().getCat().getViews().getDisplayCatImageView().setDisplayCatImageController(controller);
        return this;
    }

    /**
     * Builds the Initialize Cat use case.
     * @return this builder
     */
    public CatDisplayUseCaseBuilder buildInitializeCatsUsecase() {
        final CatViewModelFactory catViewModelFactory = new CatViewModelFactory();

        final InitializeCatsOutputBoundary outputBoundary =
                new InitializeCatsPresenter(getViews().getShared().getViewModels().getInitializeCatsViewModel(),
                        catViewModelFactory);
        final InitializeCatsInputBoundary interactor =
                new InitializeCatsInteractor(getDataAccess().getCatDataAccess(), outputBoundary);

        final InitializeCatsController controller = new InitializeCatsController(interactor);
        // add controller to login and sign up?
        getViews().getAuth().getViews().getLoginView().setInitializeCatsController(controller);
        return this;
    }

    /**
     * Builds all cat display-related use cases.
     * @return this builder
     */
    public CatDisplayUseCaseBuilder build() {
        return this
                .buildDisplayCatImageUseCase()
                .buildInitializeCatsUsecase();
    }
}
