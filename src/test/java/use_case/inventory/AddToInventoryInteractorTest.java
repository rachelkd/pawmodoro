package use_case.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

import data_access.InMemoryInventoryDataAccessObject;
import entity.AbstractFood;
import entity.FoodFactory;
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
        final AddToInventoryInputData inputData = new AddToInventoryInputData("chiually", 10);
        final InMemoryInventoryDataAccessObject inventoryRepository = new InMemoryInventoryDataAccessObject();

        final InventoryFactory inventoryFactory = new FoodInventoryFactory();
        final FoodFactory foodItemFactory = new FoodItemFactory();
        // create inventory for testing
        final Inventory inventory = inventoryFactory.create("chiually");
        inventoryRepository.save(inventory);

        final AddToInventoryOutputBoundary successPresenter = new AddToInventoryOutputBoundary() {
            @Override
            public void prepareSuccessView(AddToInventoryOutputData outputData) {
                // check that output data contains username and food id
                assertTrue(outputData.getFoodItems().containsKey("Milk"));
                assertEquals(1, outputData.getFood().getQuantity());
            }
        };

        final AddToInventoryInputBoundary interactor = new AddToInventoryInteractor(inventoryRepository,
                successPresenter, foodItemFactory);
        interactor.execute(inputData);

    }

    @Test
    void successAddToInventoryWithItemTest() {
        final InMemoryInventoryDataAccessObject inventoryRepository = new InMemoryInventoryDataAccessObject();
        final AddToInventoryInputData inputData = new AddToInventoryInputData("chiually", 10);
        final InventoryFactory inventoryFactory = new FoodInventoryFactory();
        final FoodFactory foodItemFactory = new FoodItemFactory();

        // create inventory for user
        final Inventory inventory = inventoryFactory.create("chiually");
        // add same food item
        final Map<String, AbstractFood> inventoryItems = inventory.getItems();
        inventoryItems.put("Milk", foodItemFactory.create("Milk"));
        inventory.setItems(inventoryItems);
        inventoryRepository.save(inventory);

        final AddToInventoryOutputBoundary successPresenter = new AddToInventoryOutputBoundary() {

            @Override
            public void prepareSuccessView(AddToInventoryOutputData outputData) {
                assertTrue(outputData.getFoodItems().containsKey("Milk"));
                assertEquals(2, outputData.getFood().getQuantity());
            }
        };
        final AddToInventoryInputBoundary interactor = new AddToInventoryInteractor(inventoryRepository,
                successPresenter, foodItemFactory);
        interactor.execute(inputData);
    }
}
