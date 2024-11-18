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
import interface_adapter.use_item_in_inventory.UseItemController;
import interface_adapter.use_item_in_inventory.UseItemPresenter;
import use_case.create_inventory.CreateInventoryInputBoundary;
import use_case.create_inventory.CreateInventoryInteractor;
import use_case.create_inventory.CreateInventoryOutputBoundary;
import use_case.food_management.add_to_inventory.AddToInventoryInputBoundary;
import use_case.food_management.add_to_inventory.AddToInventoryInteractor;
import use_case.food_management.add_to_inventory.AddToInventoryOutputBoundary;
import use_case.food_management.use_item_in_inventory.UseItemInputBoundary;
import use_case.food_management.use_item_in_inventory.UseItemInteractor;
import use_case.food_management.use_item_in_inventory.UseItemOutputBoundary;

/**
 * Builder for inventory-related use cases.
 */
public class InventoryUseCaseBuilder extends AbstractUseCaseBuilder {
    private final FoodItemFactory foodItemFactory;

    public InventoryUseCaseBuilder(Views views, DataAccessComponents dataAccess) {
        super(views, dataAccess);
        this.foodItemFactory = new FoodItemFactory();
    }

    /**
     * Builds the create inventory use case.
     *
     * @return this builder for method chaining
     */
    public InventoryUseCaseBuilder buildCreateInventoryUseCase() {
        final InventoryFactory inventoryFactory = new FoodInventoryFactory();
        final CreateInventoryOutputBoundary outputBoundary = new CreateInventoryPresenter(
                getViews().getViewManagerModel(),
                getViews().getSession().getViewModels().getInventoryViewModel());

        final CreateInventoryInputBoundary interactor = new CreateInventoryInteractor(
                getDataAccess().getInventoryDataAccess(),
                outputBoundary, inventoryFactory);

        final CreateInventoryController controller = new CreateInventoryController(interactor);
        getViews().getSession().getViews().getInventoryView().setCreateInventoryController(controller);
        return this;
    }

    /**
     * Builds the add to inventory use case.
     *
     * @return this builder for method chaining
     */
    public InventoryUseCaseBuilder buildAddToInventoryUseCase() {
        final AddToInventoryOutputBoundary outputBoundary = new AddToInventoryPresenter(
                getViews().getSession().getViewModels().getInventoryViewModel());

        final AddToInventoryInputBoundary interactor = new AddToInventoryInteractor(
                getDataAccess().getInventoryDataAccess(),
                outputBoundary,
                foodItemFactory);

        final AddToInventoryController controller = new AddToInventoryController(interactor);
        getViews().getSession().getViews().getInventoryView().setAddToInventoryController(controller);
        return this;
    }

    /**
     * Builds the use item use case.
     *
     * @return this builder for method chaining
     */
    public InventoryUseCaseBuilder buildUseItemUseCase() {
        final UseItemOutputBoundary outputBoundary = new UseItemPresenter(
                getViews().getSession().getViewModels().getInventoryViewModel());

        final UseItemInputBoundary interactor = new UseItemInteractor(
                getDataAccess().getInventoryDataAccess(),
                outputBoundary);

        final UseItemController controller = new UseItemController(interactor);
        getViews().getSession().getViews().getInventoryView().setUseItemController(controller);
        return this;
    }

    /**
     * Builds all inventory-related use cases.
     *
     * @return this builder for method chaining
     */
    public InventoryUseCaseBuilder buildInventoryUseCases() {
        return this
                .buildCreateInventoryUseCase()
                .buildAddToInventoryUseCase()
                .buildUseItemUseCase();
    }
}
