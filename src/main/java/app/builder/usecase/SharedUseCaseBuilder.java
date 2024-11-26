package app.builder.usecase;

import app.builder.view.Views;
import app.components.DataAccessComponents;
import entity.FoodInventoryFactory;
import entity.InventoryFactory;
import interface_adapter.create_inventory.CreateInventoryController;
import interface_adapter.create_inventory.CreateInventoryPresenter;
import interface_adapter.display_cat_stats.DisplayCatStatsController;
import interface_adapter.display_cat_stats.DisplayCatStatsPresenter;
import interface_adapter.get_cat_fact.GetCatFactController;
import interface_adapter.get_cat_fact.GetCatFactPresenter;
import use_case.display_cat_stats.DisplayCatStatsInputBoundary;
import use_case.display_cat_stats.DisplayCatStatsInteractor;
import use_case.display_cat_stats.DisplayCatStatsOutputBoundary;
import use_case.food_management.create_inventory.CreateInventoryInputBoundary;
import use_case.food_management.create_inventory.CreateInventoryInteractor;
import use_case.food_management.create_inventory.CreateInventoryOutputBoundary;
import use_case.get_cat_fact.GetCatFactInputBoundary;
import use_case.get_cat_fact.GetCatFactInteractor;
import use_case.get_cat_fact.GetCatFactOutputBoundary;

/**
 * Builder for shared use cases.
 */
public class SharedUseCaseBuilder extends AbstractUseCaseBuilder {

    public SharedUseCaseBuilder(Views views, DataAccessComponents dataAccess) {
        super(views, dataAccess);
    }

    /**
     * Builds the display cat stats use case.
     * @return this builder
     */
    public SharedUseCaseBuilder buildDisplayCatStatsUseCase() {
        final DisplayCatStatsOutputBoundary presenter = new DisplayCatStatsPresenter(
                getViews().getShared().getViewModels().getDisplayCatStatsViewModel());

        final DisplayCatStatsInputBoundary interactor = new DisplayCatStatsInteractor(
                getDataAccess().getCatDataAccess(),
                presenter);

        final DisplayCatStatsController controller = new DisplayCatStatsController(interactor);
        getViews().getShared().getViews().getCatView().setDisplayCatStatsController(controller);
        return this;
    }

    /**
     * Builds the get cat fact use case.
     * @return this builder
     */
    public SharedUseCaseBuilder buildGetCatFactUseCase() {
        final GetCatFactOutputBoundary presenter = new GetCatFactPresenter(
                getViews().getShared().getViewModels().getGetCatFactViewModel());

        final GetCatFactInputBoundary interactor = new GetCatFactInteractor(
                getDataAccess().getCatFactDataAccess(),
                presenter);

        final GetCatFactController controller = new GetCatFactController(interactor);
        getViews().getShared().getViews().getGetCatFactView().setGetCatFactController(controller);
        return this;
    }

    /**
     * Builds the create inventory use case.
     * @return this builder
     */
    public SharedUseCaseBuilder buildCreateInventoryUseCase() {
        final InventoryFactory inventoryFactory = new FoodInventoryFactory();
        final CreateInventoryOutputBoundary presenter = new CreateInventoryPresenter(
                getViews().getShared().getViewModels().getInventoryViewModel());

        final CreateInventoryInputBoundary interactor = new CreateInventoryInteractor(
                getDataAccess().getInventoryDataAccess(),
                presenter,
                inventoryFactory);

        final CreateInventoryController controller = new CreateInventoryController(interactor);
        getViews().getShared().getViews().getInventoryView().setCreateInventoryController(controller);
        return this;
    }

    /**
     * Builds all shared use cases.
     * @return this builder
     */
    public SharedUseCaseBuilder build() {
        return this
                .buildDisplayCatStatsUseCase()
                .buildGetCatFactUseCase()
                .buildCreateInventoryUseCase();
    }
}
