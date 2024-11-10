package use_case.inventory;

import data_access.InMemoryInventoryDataAccessObject;
import entity.*;
import org.junit.jupiter.api.Test;
import use_case.authentication.create_inventory.*;
import use_case.food_management.add_to_inventory.*;

import static org.junit.jupiter.api.Assertions.*;


public class InventoryInteractorTest {

    @Test
    void AddToInventoryTest() {
        final AddToInventoryInputData inputData = new AddToInventoryInputData("chiually", "milk");
        final InMemoryInventoryDataAccessObject inventoryRepository = new InMemoryInventoryDataAccessObject();


        final InventoryFactory inventoryFactory = new FoodInventoryFactory();
        final FoodItemFactory foodItemFactory = new FoodItemFactory();
        final Inventory inventory = inventoryFactory.create("chiually");
        inventoryRepository.save(inventory);

        final AddToInventoryOutputBoundary successPresenter = new AddToInventoryOutputBoundary() {
            @Override
            public void prepareSuccessView(AddToInventoryOutputData inventory) {
                // check that output data contains username and food id
                assertEquals("chiually", inventory.getOwnerId());
                assertEquals("milk", inventory.getFoodId());
            }
        };

        final AddToInventoryInputBoundary interactor = new AddToInventoryInteractor(inventoryRepository,
                successPresenter, foodItemFactory);
        interactor.execute(inputData);

    }

    @Test
    void CreateInventoryTest() {
        final CreateInventoryInputData inputData = new CreateInventoryInputData("chiually");
        final InMemoryInventoryDataAccessObject inventoryRepository = new InMemoryInventoryDataAccessObject();

        final InventoryFactory inventoryFactory = new FoodInventoryFactory();


        final CreateInventoryOutputBoundary successPresenter = new CreateInventoryOutputBoundary() {

            @Override
            public void prepareSuccessView(CreateInventoryOutputData inventory) {
                assertEquals("chiually", inventory.getOwnerId());
                assertTrue(inventoryRepository.existsByOwnerId(inventory.getOwnerId()));
            }
        };

        final CreateInventoryInputBoundary interactor = new CreateInventoryInteractor(inventoryRepository,
                successPresenter, inventoryFactory);
        interactor.execute(inputData);
    }

}
