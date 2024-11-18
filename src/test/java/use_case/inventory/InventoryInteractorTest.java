package use_case.inventory;

import data_access.InMemoryInventoryDataAccessObject;
import entity.*;
import org.junit.jupiter.api.Test;
import use_case.create_inventory.*;
import use_case.food_management.add_to_inventory.*;
import use_case.food_management.use_item_in_inventory.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class InventoryInteractorTest {

    @Test
    void addToEmptyInventoryTest() {
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
    void addToInventoryWithItemTest() {
        final InMemoryInventoryDataAccessObject inventoryRepository = new InMemoryInventoryDataAccessObject();
        final AddToInventoryInputData inputData = new AddToInventoryInputData("chiually", "milk");
        final InventoryFactory inventoryFactory = new FoodInventoryFactory();
        final FoodItemFactory foodItemFactory = new FoodItemFactory();

        // create inventory for user
        final Inventory inventory = inventoryFactory.create("chiually");
        //add same food item
        Map<String, AbstractFood> inventoryItems = inventory.getItems();
        inventoryItems.put("milk", foodItemFactory.create("milk", "temp"));
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

    @Test
    void useLastItemTest() {
        final UseItemInputData inputData = new UseItemInputData("chiually", "milk");
        final InMemoryInventoryDataAccessObject inventoryRepository = new InMemoryInventoryDataAccessObject();

        final InventoryFactory inventoryFactory = new FoodInventoryFactory();
        final FoodItemFactory foodItemFactory = new FoodItemFactory();

        final Inventory inventory = inventoryFactory.create("chiually");
        final AbstractFood foodItem = foodItemFactory.create("milk", "temp");
        foodItem.setQuantity(1);
        Map<String, AbstractFood> inventoryItems = inventory.getItems();
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
    void useOneOfMultipleItemTest() {
        final UseItemInputData inputData = new UseItemInputData("chiually", "milk");
        final InMemoryInventoryDataAccessObject inventoryRepository = new InMemoryInventoryDataAccessObject();

        final InventoryFactory inventoryFactory = new FoodInventoryFactory();
        final FoodItemFactory foodItemFactory = new FoodItemFactory();

        final Inventory inventory = inventoryFactory.create("chiually");
        final AbstractFood foodItem = foodItemFactory.create("milk", "temp");
        foodItem.setQuantity(2);
        Map<String, AbstractFood> inventoryItems = inventory.getItems();
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
    void createNonExistentInventoryTest() {
        final CreateInventoryInputData inputData = new CreateInventoryInputData("chiually");
        final InMemoryInventoryDataAccessObject inventoryRepository = new InMemoryInventoryDataAccessObject();

        final InventoryFactory inventoryFactory = new FoodInventoryFactory();


        final CreateInventoryOutputBoundary successPresenter = new CreateInventoryOutputBoundary() {

            @Override
            public void prepareSuccessView(CreateInventoryOutputData inventory) {
                assertTrue(inventory.isSuccess());
            }
        };

        final CreateInventoryInputBoundary interactor = new CreateInventoryInteractor(inventoryRepository,
                successPresenter, inventoryFactory);
        interactor.execute(inputData);
    }

    @Test
    void createExistingInventoryTest() {
        final CreateInventoryInputData inputData = new CreateInventoryInputData("chiually");
        final InMemoryInventoryDataAccessObject inventoryRepository = new InMemoryInventoryDataAccessObject();

        final InventoryFactory inventoryFactory = new FoodInventoryFactory();
        final FoodItemFactory foodItemFactory = new FoodItemFactory();

        Inventory inventory = inventoryFactory.create("chiually");
        final AbstractFood foodItem = foodItemFactory.create("milk", "temp");
        foodItem.setQuantity(2);
        Map<String, AbstractFood> inventoryItems = inventory.getItems();
        inventoryItems.put("milk", foodItem);
        inventory.setItems(inventoryItems);
        inventoryRepository.save(inventory);

        final CreateInventoryOutputBoundary successPresenter = new CreateInventoryOutputBoundary() {

            @Override
            public void prepareSuccessView(CreateInventoryOutputData inventory) {

                assertTrue(inventory.isSuccess());
                assert(inventory.getInventoryItems().containsKey("milk"));
                assertEquals(2, inventory.getInventoryItems().get("milk").getQuantity());
            }
        };

        final CreateInventoryInputBoundary interactor = new CreateInventoryInteractor(inventoryRepository,
                successPresenter, inventoryFactory);
        interactor.execute(inputData);
    }

}
