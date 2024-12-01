package use_case.inventory;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data_access.InMemoryInventoryDataAccessObject;
import entity.FoodFactory;
import entity.FoodInventoryFactory;
import entity.FoodItemFactory;
import entity.Inventory;
import entity.InventoryFactory;
import use_case.food_management.use_item_in_inventory.UseItemInputBoundary;
import use_case.food_management.use_item_in_inventory.UseItemInputData;
import use_case.food_management.use_item_in_inventory.UseItemInteractor;
import use_case.food_management.use_item_in_inventory.UseItemOutputBoundary;
import use_case.food_management.use_item_in_inventory.UseItemOutputData;

/**
 * Testing the Use Item Interactor.
 */
class UseItemInteractorTest {
    private FoodFactory foodFactory;
    private InventoryFactory inventoryFactory;
    private InMemoryInventoryDataAccessObject inventoryRepository;

    @BeforeEach
    void setUp() {
        this.foodFactory = new FoodItemFactory();
        this.inventoryFactory = new FoodInventoryFactory();
        this.inventoryRepository = new InMemoryInventoryDataAccessObject();
    }

    @Test
    void successUseLastItemTest() {
        final UseItemInputData inputData = new UseItemInputData("chiually", "Milk");

        final Inventory inventory = inventoryFactory.create("chiually");

        final Map<String, Integer> inventoryItems = inventory.getItems();
        inventoryItems.put("Milk", 1);
        inventory.setItems(inventoryItems);
        inventoryRepository.save(inventory);

        final UseItemOutputBoundary successPresenter = new UseItemOutputBoundary() {

            @Override
            public void prepareSuccessView(UseItemOutputData useInventoryOutputData) {
                assertFalse(useInventoryOutputData.getNewFoodItems().containsKey("Milk"));
            }
        };
        final UseItemInputBoundary interactor = new UseItemInteractor(inventoryRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void successUseOneOfMultipleItemTest() {
        final UseItemInputData inputData = new UseItemInputData("chiually", "Milk");

        final Inventory inventory = inventoryFactory.create("chiually");
        final Map<String, Integer> inventoryItems = inventory.getItems();
        inventoryItems.put("Milk", 2);
        inventory.setItems(inventoryItems);
        inventoryRepository.save(inventory);

        final UseItemOutputBoundary successPresenter = new UseItemOutputBoundary() {

            @Override
            public void prepareSuccessView(UseItemOutputData useInventoryOutputData) {
                assertTrue(useInventoryOutputData.getNewFoodItems().containsKey("Milk"));
                assertEquals(1, useInventoryOutputData.getNewFoodItems().get("Milk"));
            }
        };
        final UseItemInputBoundary interactor = new UseItemInteractor(inventoryRepository, successPresenter);
        interactor.execute(inputData);
    }
}
