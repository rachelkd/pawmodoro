package app.builder.usecase;

import app.builder.view.Views;
import app.components.DataAccessComponents;
import entity.FoodInventoryFactory;
import entity.FoodItemFactory;
import entity.InventoryFactory;
import interface_adapter.add_to_inventory.AddToInventoryController;
import interface_adapter.add_to_inventory.AddToInventoryPresenter;
import interface_adapter.create_inventory.CreateInventoryController;
import interface_adapter.create_inventory.CreateInventoryPresenter;
import interface_adapter.display_cat_stats.DisplayCatStatsController;
import interface_adapter.display_cat_stats.DisplayCatStatsPresenter;
import interface_adapter.get_cat_fact.GetCatFactController;
import interface_adapter.get_cat_fact.GetCatFactPresenter;
import interface_adapter.use_item_in_inventory.UseItemController;
import interface_adapter.use_item_in_inventory.UseItemPresenter;
import use_case.display_cat_stats.DisplayCatStatsInputBoundary;
import use_case.display_cat_stats.DisplayCatStatsInteractor;
import use_case.display_cat_stats.DisplayCatStatsOutputBoundary;
import use_case.food_management.add_to_inventory.AddToInventoryInputBoundary;
import use_case.food_management.add_to_inventory.AddToInventoryInteractor;
import use_case.food_management.add_to_inventory.AddToInventoryOutputBoundary;
import use_case.food_management.create_inventory.CreateInventoryInputBoundary;
import use_case.food_management.create_inventory.CreateInventoryInteractor;
import use_case.food_management.create_inventory.CreateInventoryOutputBoundary;
import use_case.food_management.use_item_in_inventory.UseItemInputBoundary;
import use_case.food_management.use_item_in_inventory.UseItemInteractor;
import use_case.food_management.use_item_in_inventory.UseItemOutputBoundary;
import use_case.get_cat_fact.GetCatFactInputBoundary;
import use_case.get_cat_fact.GetCatFactInteractor;
import use_case.get_cat_fact.GetCatFactOutputBoundary;

/**
 * Builder for shared use cases.
 */
public class SharedUseCaseBuilder extends AbstractUseCaseBuilder {
    private FoodItemFactory foodItemFactory;

    public SharedUseCaseBuilder(Views views, DataAccessComponents dataAccess) {
        super(views, dataAccess);
        this.foodItemFactory = new FoodItemFactory();
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
        getViews().getAuth().getViews().getSignupView().setCreateInventoryController(controller);
        getViews().getAuth().getViews().getLoginView().setCreateInventoryController(controller);
        return this;
    }

    /**
     * Builds the add to inventory use case.
     *
     * @return this builder
     */
    public SharedUseCaseBuilder buildAddToInventoryUseCase() {
        final AddToInventoryOutputBoundary outputBoundary = new AddToInventoryPresenter(
                getViews().getShared().getViewModels().getInventoryViewModel());

        final AddToInventoryInputBoundary interactor = new AddToInventoryInteractor(
                getDataAccess().getInventoryDataAccess(),
                outputBoundary,
                foodItemFactory);

        final AddToInventoryController controller = new AddToInventoryController(interactor);
        getViews().getShared().getViews().getInventoryView().setAddToInventoryController(controller);
        getViews().getSession().getViews().getStudySessionView().setAddToInventoryController(controller);
        getViews().getAuth().getViews().getLoginView().setAddToInventoryController(controller);
        return this;
    }

    /**
     * Builds the use item use case.
     *
     * @return this builder
     */
    public SharedUseCaseBuilder buildUseItemUseCase() {
        final UseItemOutputBoundary outputBoundary = new UseItemPresenter(
                getViews().getShared().getViewModels().getInventoryViewModel());

        final UseItemInputBoundary interactor = new UseItemInteractor(
                getDataAccess().getInventoryDataAccess(),
                outputBoundary);

        final UseItemController controller = new UseItemController(interactor);
        getViews().getShared().getViews().getInventoryView().setUseItemController(controller);
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
                .buildCreateInventoryUseCase()
                .buildAddToInventoryUseCase()
                .buildUseItemUseCase();
    }
}
