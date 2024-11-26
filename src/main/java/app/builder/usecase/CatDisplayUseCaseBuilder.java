package app.builder.usecase;

import app.builder.view.Views;
import app.components.DataAccessComponents;
import interface_adapter.display_cat_image.DisplayCatImageController;
import interface_adapter.display_cat_image.DisplayCatImagePresenter;
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
     * Builds all cat display-related use cases.
     * @return this builder
     */
    public CatDisplayUseCaseBuilder build() {
        return this.buildDisplayCatImageUseCase();
    }
}
