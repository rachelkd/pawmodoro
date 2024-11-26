package app.builder.usecase;

import app.builder.view.Views;
import app.components.DataAccessComponents;
import interface_adapter.display_cat_image.DisplayCatImageController;
import interface_adapter.display_cat_image.DisplayCatImagePresenter;
import interface_adapter.display_cat_stats.DisplayCatStatsController;
import interface_adapter.display_cat_stats.DisplayCatStatsPresenter;
import interface_adapter.display_cat_stats.DisplayCatStatsViewModel;
import interface_adapter.get_cat_fact.GetCatFactController;
import interface_adapter.get_cat_fact.GetCatFactPresenter;
import use_case.display_cat_image.DisplayCatImageInputBoundary;
import use_case.display_cat_image.DisplayCatImageInteractor;
import use_case.display_cat_image.DisplayCatImageOutputBoundary;
import use_case.display_cat_stats.DisplayCatStatsInputBoundary;
import use_case.display_cat_stats.DisplayCatStatsInteractor;
import use_case.display_cat_stats.DisplayCatStatsOutputBoundary;
import use_case.get_cat_fact.GetCatFactInputBoundary;
import use_case.get_cat_fact.GetCatFactInteractor;
import use_case.get_cat_fact.GetCatFactOutputBoundary;

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
     * Builds the display cat stats use case.
     * @return this builder
     */
    public CatDisplayUseCaseBuilder buildDisplayCatStatsUseCase() {
        final DisplayCatStatsViewModel vm1 = getViews().getSession().getViewModels().getDisplayCatStatsViewModel();
        System.out.println("Presenter ViewModel: " + vm1);
        final DisplayCatStatsOutputBoundary presenter = new DisplayCatStatsPresenter(
                getViews().getSession().getViewModels().getDisplayCatStatsViewModel());

        final DisplayCatStatsInputBoundary interactor = new DisplayCatStatsInteractor(
                getDataAccess().getCatDataAccess(),
                presenter);

        final DisplayCatStatsController controller = new DisplayCatStatsController(interactor);
        getViews().getCat().getViews().getCatView().setDisplayCatStatsController(controller);
        return this;
    }

    /**
     * Builds the get cat fact use case.
     * @return this builder
     */
    public CatDisplayUseCaseBuilder buildGetCatFactUseCase() {
        final GetCatFactOutputBoundary getCatFactPresenter = new GetCatFactPresenter(
                getViews().getCat().getViewModels().getGetCatFactViewModel());

        final GetCatFactInputBoundary getCatFactInteractor = new GetCatFactInteractor(
                getDataAccess().getCatFactDataAccess(),
                getCatFactPresenter);

        final GetCatFactController getCatFactController = new GetCatFactController(getCatFactInteractor);
        getViews().getCat().getViews().getGetCatFactView().setGetCatFactController(getCatFactController);
        return this;
    }

    /**
     * Builds all cat display-related use cases.
     * @return this builder
     */
    public CatDisplayUseCaseBuilder build() {
        return this
                .buildDisplayCatImageUseCase()
                .buildDisplayCatStatsUseCase()
                .buildGetCatFactUseCase();
    }
}
