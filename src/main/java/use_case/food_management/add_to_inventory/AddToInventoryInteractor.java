package use_case.food_management.add_to_inventory;

import java.util.Map;

import entity.Inventory;
import use_case.food_management.InventoryDataAccessInterface;

/**
 * The Add To Inventory Interactor.
 */
public class AddToInventoryInteractor implements AddToInventoryInputBoundary {
    private final InventoryDataAccessInterface inventoryDataAccessObject;
    private final AddToInventoryOutputBoundary addToInventoryPresenter;
    private final FoodMappingService foodMappingService;

    public AddToInventoryInteractor(InventoryDataAccessInterface inventoryDataAccessObject,
                                    AddToInventoryOutputBoundary addToInventoryOutputBoundary) {
        // repository of inventories
        this.inventoryDataAccessObject = inventoryDataAccessObject;
        this.addToInventoryPresenter = addToInventoryOutputBoundary;
        this.foodMappingService = new FoodMappingService();
    }

    @Override
    public void execute(AddToInventoryInputData addToInventoryInputData) {
        // assume existing inventory
        final String foodName = foodMappingService.getFoodName(addToInventoryInputData.getStudySessionLength());
        final Inventory inventory = inventoryDataAccessObject.getInventory(addToInventoryInputData.getOwnerId());
        final Map<String, Integer> foodItems = inventoryDataAccessObject
                .getInventoryItems(addToInventoryInputData.getOwnerId());

        // item in inventory already
        if (inventory.getItems().containsKey(foodName)) {

            final int prevQuantity = inventoryDataAccessObject
                    .getInventory(addToInventoryInputData.getOwnerId())
                    .getItems().get(foodName);

            foodItems.put(foodName, prevQuantity + 1);

            // since food object mutable
            inventoryDataAccessObject.updateInventory(inventory);
        } // item not in inventory
        else {
            foodItems.put(foodName, 1);
            inventoryDataAccessObject.updateInventory(inventory);
        }

        final AddToInventoryOutputData addToInventoryOutputData =
                new AddToInventoryOutputData(addToInventoryInputData.getOwnerId(), foodName,
                        inventoryDataAccessObject.getInventoryItems(addToInventoryInputData.getOwnerId()));
        addToInventoryPresenter.prepareSuccessView(addToInventoryOutputData);
    }

}
