package use_case.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data_access.InMemoryInventoryDataAccessObject;
import entity.FoodInventoryFactory;
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
    private InventoryFactory inventoryFactory;
    private InMemoryInventoryDataAccessObject inventoryRepository;

    @BeforeEach
    void setUp() {
        inventoryFactory = new FoodInventoryFactory();
        inventoryRepository = new InMemoryInventoryDataAccessObject();
    }

    @Test
    void successAddToEmptyInventoryTest() {
        final AddToInventoryInputData inputData = new AddToInventoryInputData("chiually", 10);

        // create inventory for testing
        final Inventory inventory = inventoryFactory.create("chiually");
        inventoryRepository.save(inventory);

        final AddToInventoryOutputBoundary successPresenter = new AddToInventoryOutputBoundary() {
            @Override
            public void prepareSuccessView(AddToInventoryOutputData outputData) {
                // check that output data contains username and food id
                assertTrue(outputData.getFoodItems().containsKey("Milk"));
                assertEquals(1, outputData.getFoodItems().get(outputData.getFoodName()));
            }
        };

        final AddToInventoryInputBoundary interactor = new AddToInventoryInteractor(inventoryRepository,
                successPresenter);
        interactor.execute(inputData);

    }

    @Test
    void successAddToInventoryWithItemTest() {
        final AddToInventoryInputData inputData = new AddToInventoryInputData("chiually", 10);

        // create inventory for user
        final Inventory inventory = inventoryFactory.create("chiually");
        // add same food item
        final Map<String, Integer> inventoryItems = inventory.getItems();
        inventoryItems.put("Milk", 1);
        inventory.setItems(inventoryItems);
        inventoryRepository.save(inventory);

        final AddToInventoryOutputBoundary successPresenter = new AddToInventoryOutputBoundary() {

            @Override
            public void prepareSuccessView(AddToInventoryOutputData outputData) {
                assertTrue(outputData.getFoodItems().containsKey("Milk"));
                assertEquals(2, outputData.getFoodItems().get(outputData.getFoodName()));
            }
        };
        final AddToInventoryInputBoundary interactor = new AddToInventoryInteractor(inventoryRepository,
                successPresenter);
        interactor.execute(inputData);
    }
}
