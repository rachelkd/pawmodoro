package use_case.inventory;

import java.util.Map;

import org.junit.jupiter.api.Test;

import data_access.InMemoryInventoryDataAccessObject;
import entity.AbstractFood;
import entity.FoodInventoryFactory;
import entity.FoodItemFactory;
import entity.Inventory;
import entity.InventoryFactory;
import use_case.food_management.create_inventory.CreateInventoryInputBoundary;
import use_case.food_management.create_inventory.CreateInventoryInputData;
import use_case.food_management.create_inventory.CreateInventoryInteractor;
import use_case.food_management.create_inventory.CreateInventoryOutputBoundary;
import use_case.food_management.create_inventory.CreateInventoryOutputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testing the Create Inventory Interactor.
 */
class CreateInventoryInteractorTest {

    @Test
    void successCreateNonExistentInventoryTest() {
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
    void successCreateExistingInventoryTest() {
        final CreateInventoryInputData inputData = new CreateInventoryInputData("chiually");
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

        final CreateInventoryOutputBoundary successPresenter = new CreateInventoryOutputBoundary() {

            @Override
            public void prepareSuccessView(CreateInventoryOutputData inventory) {

                assertTrue(inventory.isSuccess());
                assertTrue(inventory.getInventoryItems().containsKey("milk"));
                assertEquals(2, inventory.getInventoryItems().get("milk").getQuantity());
            }
        };

        final CreateInventoryInputBoundary interactor = new CreateInventoryInteractor(inventoryRepository,
                successPresenter, inventoryFactory);
        interactor.execute(inputData);
    }
}
