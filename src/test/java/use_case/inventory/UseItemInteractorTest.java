package use_case.inventory;

import java.util.Map;

import org.junit.jupiter.api.Test;

import data_access.InMemoryInventoryDataAccessObject;
import entity.AbstractFood;
import entity.FoodInventoryFactory;
import entity.FoodItemFactory;
import entity.Inventory;
import entity.InventoryFactory;
import use_case.food_management.use_item_in_inventory.UseItemInputBoundary;
import use_case.food_management.use_item_in_inventory.UseItemInputData;
import use_case.food_management.use_item_in_inventory.UseItemInteractor;
import use_case.food_management.use_item_in_inventory.UseItemOutputBoundary;
import use_case.food_management.use_item_in_inventory.UseItemOutputData;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testing the Use Item Interactor.
 */
class UseItemInteractorTest {

    @Test
    void successUseLastItemTest() {
        final UseItemInputData inputData = new UseItemInputData("chiually", "milk");
        final InMemoryInventoryDataAccessObject inventoryRepository = new InMemoryInventoryDataAccessObject();

        final InventoryFactory inventoryFactory = new FoodInventoryFactory();
        final FoodItemFactory foodItemFactory = new FoodItemFactory();

        final Inventory inventory = inventoryFactory.create("chiually");
        final AbstractFood foodItem = foodItemFactory.create("milk");
        foodItem.setQuantity(1);
        final Map<String, AbstractFood> inventoryItems = inventory.getItems();
        inventoryItems.put("milk", foodItem);
        inventory.setItems(inventoryItems);
        inventoryRepository.save(inventory);

        final UseItemOutputBoundary successPresenter = new UseItemOutputBoundary() {

            @Override
            public void prepareSuccessView(UseItemOutputData useInventoryOutputData) {
                assertTrue(useInventoryOutputData.isSuccess());
            }
        };
        final UseItemInputBoundary interactor = new UseItemInteractor(inventoryRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void successUseOneOfMultipleItemTest() {
        final UseItemInputData inputData = new UseItemInputData("chiually", "milk");
        final InMemoryInventoryDataAccessObject inventoryRepository = new InMemoryInventoryDataAccessObject();

        final InventoryFactory inventoryFactory = new FoodInventoryFactory();
        final FoodItemFactory foodItemFactory = new FoodItemFactory();

        final Inventory inventory = inventoryFactory.create("chiually");
        final AbstractFood foodItem = foodItemFactory.create("milk");
        foodItem.setQuantity(2);
        final Map<String, AbstractFood> inventoryItems = inventory.getItems();
        inventoryItems.put("milk", foodItem);
        inventory.setItems(inventoryItems);
        inventoryRepository.save(inventory);

        final UseItemOutputBoundary successPresenter = new UseItemOutputBoundary() {

            @Override
            public void prepareSuccessView(UseItemOutputData useInventoryOutputData) {
                assertTrue(useInventoryOutputData.isSuccess());
            }
        };
        final UseItemInputBoundary interactor = new UseItemInteractor(inventoryRepository, successPresenter);
        interactor.execute(inputData);
    }
}
