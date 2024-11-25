package use_case.inventory;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

import data_access.InMemoryInventoryDataAccessObject;
import entity.AbstractFood;
import entity.FoodInventoryFactory;
import entity.FoodItemFactory;
import entity.Inventory;
import entity.InventoryFactory;
import use_case.food_management.add_to_inventory.AddToInventoryInputBoundary;
import use_case.food_management.add_to_inventory.AddToInventoryInputData;
import use_case.food_management.add_to_inventory.AddToInventoryInteractor;
import use_case.food_management.add_to_inventory.AddToInventoryOutputBoundary;
import use_case.food_management.add_to_inventory.AddToInventoryOutputData;

/**
 * Testing the Add To Inventory Use Case.
 */
class AddToInventoryInteractorTest {

    @Test
    void successAddToEmptyInventoryTest() {
        final AddToInventoryInputData inputData = new AddToInventoryInputData("chiually", "milk");
        final InMemoryInventoryDataAccessObject inventoryRepository = new InMemoryInventoryDataAccessObject();

        final InventoryFactory inventoryFactory = new FoodInventoryFactory();
        final FoodItemFactory foodItemFactory = new FoodItemFactory();
        // create inventory for testing
        final Inventory inventory = inventoryFactory.create("chiually");
        inventoryRepository.save(inventory);

        final AddToInventoryOutputBoundary successPresenter = new AddToInventoryOutputBoundary() {
            @Override
            public void prepareSuccessView(AddToInventoryOutputData inventory) {
                // check that output data contains username and food id
                assertTrue(inventory.isSuccess());
            }
        };

        final AddToInventoryInputBoundary interactor = new AddToInventoryInteractor(inventoryRepository,
                successPresenter, foodItemFactory);
        interactor.execute(inputData);

    }

    @Test
    void successAddToInventoryWithItemTest() {
        final InMemoryInventoryDataAccessObject inventoryRepository = new InMemoryInventoryDataAccessObject();
        final AddToInventoryInputData inputData = new AddToInventoryInputData("chiually", "milk");
        final InventoryFactory inventoryFactory = new FoodInventoryFactory();
        final FoodItemFactory foodItemFactory = new FoodItemFactory();

        // create inventory for user
        final Inventory inventory = inventoryFactory.create("chiually");
        // add same food item
        final Map<String, AbstractFood> inventoryItems = inventory.getItems();
        inventoryItems.put("milk", foodItemFactory.create("milk"));
        inventory.setItems(inventoryItems);
        inventoryRepository.save(inventory);

        final AddToInventoryOutputBoundary successPresenter = new AddToInventoryOutputBoundary() {

            @Override
            public void prepareSuccessView(AddToInventoryOutputData inventory) {
                assertTrue(inventory.isSuccess());
            }
        };
        final AddToInventoryInputBoundary interactor = new AddToInventoryInteractor(inventoryRepository,
                successPresenter, foodItemFactory);
        interactor.execute(inputData);
    }
}
