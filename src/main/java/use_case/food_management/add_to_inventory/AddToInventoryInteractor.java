package use_case.food_management.add_to_inventory;

import java.util.Map;

import entity.AbstractFood;
import entity.FoodItemFactory;
import entity.Inventory;
import use_case.food_management.FoodMappingService;
import use_case.food_management.InventoryDataAccessInterface;

/**
 * The Add To Inventory Interactor.
 */
public class AddToInventoryInteractor implements AddToInventoryInputBoundary {
    private final InventoryDataAccessInterface inventoryDataAccessObject;
    private final AddToInventoryOutputBoundary addToInventoryPresenter;
    private final FoodItemFactory foodFactory;
    private final FoodMappingService foodMappingService;

    public AddToInventoryInteractor(InventoryDataAccessInterface inventoryDataAccessObject,
                                    AddToInventoryOutputBoundary addToInventoryOutputBoundary,
                                    FoodItemFactory foodFactory) {
        // repository of inventories
        this.inventoryDataAccessObject = inventoryDataAccessObject;
        this.addToInventoryPresenter = addToInventoryOutputBoundary;
        this.foodFactory = foodFactory;
        this.foodMappingService = new FoodMappingService();
    }

    @Override
    public void execute(AddToInventoryInputData addToInventoryInputData) {
        // assume existing inventory
        final boolean isSuccess;
        final AbstractFood foodItem;

        // item in inventory already
        if (inventoryDataAccessObject.getInventory(addToInventoryInputData.getOwnerId())
                .getItems()
                .containsKey(foodMappingService.getFoodName(addToInventoryInputData.getStudySessionLength()))) {

            final String foodName = foodMappingService.getFoodName(addToInventoryInputData.getStudySessionLength());

            final int prevQuantity = inventoryDataAccessObject
                    .getInventory(addToInventoryInputData.getOwnerId())
                    .getItems().get(foodName).getQuantity();

            final Inventory inventory = inventoryDataAccessObject.getInventory(addToInventoryInputData.getOwnerId());
            foodItem = inventory.getItems().get(foodName);
            foodItem.setQuantity(prevQuantity + 1);
            // since food object mutable
            inventoryDataAccessObject.updateInventory(inventory);

            isSuccess = inventoryDataAccessObject.getInventory(addToInventoryInputData.getOwnerId())
                    .getItems().containsKey(foodName) && inventoryDataAccessObject
                    .getInventory(addToInventoryInputData.getOwnerId())
                    .getItems().get(foodName).getQuantity() == prevQuantity + 1;

        } // item not in inventory
        else {
            foodItem = foodFactory.create(addToInventoryInputData.getStudySessionLength());
            foodItem.setQuantity(1);

            final Map<String, AbstractFood> newInventoryItems = inventoryDataAccessObject
                    .getInventory(addToInventoryInputData.getOwnerId())
                    .getItems();

            newInventoryItems.put(foodItem.getName(), foodItem);

            final Inventory inventory = inventoryDataAccessObject.getInventory(addToInventoryInputData.getOwnerId());

            inventory.setItems(newInventoryItems);

            inventoryDataAccessObject.updateInventory(inventory);

            isSuccess = inventoryDataAccessObject.getInventory(addToInventoryInputData.getOwnerId())
                    .getItems().containsKey(foodItem.getName())
                    && inventoryDataAccessObject.getInventory(addToInventoryInputData.getOwnerId())
                    .getItems().get(foodItem.getName()).getQuantity() == 1;
        }

        final AddToInventoryOutputData addToInventoryOutputData =
                new AddToInventoryOutputData(isSuccess, addToInventoryInputData.getOwnerId(), foodItem);
        addToInventoryPresenter.prepareSuccessView(addToInventoryOutputData);

    }

}
