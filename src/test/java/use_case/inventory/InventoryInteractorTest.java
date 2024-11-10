package use_case.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import data_access.InMemoryInventoryDataAccessObject;
import entity.*;
import org.junit.jupiter.api.Test;
import use_case.food_management.add_to_inventory.*;


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

}
