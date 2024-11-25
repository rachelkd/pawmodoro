package use_case.food_management.add_to_inventory;

import java.util.Map;

import entity.AbstractFood;
import entity.FoodItemFactory;
import entity.Inventory;
import use_case.food_management.InventoryDataAccessInterface;

/**
 * The Add To Inventory Interactor.
 */
public class AddToInventoryInteractor implements AddToInventoryInputBoundary {
    private final InventoryDataAccessInterface inventoryDataAccessObject;
    private final AddToInventoryOutputBoundary addToInventoryPresenter;
    private final FoodItemFactory foodFactory;

    public AddToInventoryInteractor(InventoryDataAccessInterface inventoryDataAccessObject,
                                    AddToInventoryOutputBoundary addToInventoryOutputBoundary,
                                    FoodItemFactory foodFactory) {
        // repository of inventories
        this.inventoryDataAccessObject = inventoryDataAccessObject;
        this.addToInventoryPresenter = addToInventoryOutputBoundary;
        this.foodFactory = foodFactory;
    }

    @Override
    public void execute(AddToInventoryInputData addToInventoryInputData) {
        // assume existing inventory
        final boolean isSuccess;
        final AbstractFood foodItem;

        // item in inventory already
        if (inventoryDataAccessObject.getInventory(addToInventoryInputData.getOwnerId())
                .getItems().containsKey(addToInventoryInputData.getFoodName())) {

            final int prevQuantity = inventoryDataAccessObject
                    .getInventory(addToInventoryInputData.getOwnerId())
                    .getItems().get(addToInventoryInputData.getFoodName()).getQuantity();
            final Inventory inventory = inventoryDataAccessObject.getInventory(addToInventoryInputData.getOwnerId());
            foodItem = inventory.getItems().get(addToInventoryInputData.getFoodName());
            foodItem.setQuantity(prevQuantity + 1);
            // since food object mutable
            inventoryDataAccessObject.updateInventory(inventory);

            isSuccess = inventoryDataAccessObject.getInventory(addToInventoryInputData.getOwnerId())
                    .getItems().containsKey(addToInventoryInputData.getFoodName()) && inventoryDataAccessObject
                    .getInventory(addToInventoryInputData.getOwnerId())
                    .getItems().get(addToInventoryInputData.getFoodName()).getQuantity() == prevQuantity + 1;

        } // item not in inventory
        else {
            foodItem = foodFactory.create(addToInventoryInputData.getFoodName());
            foodItem.setQuantity(1);

            final Map<String, AbstractFood> newInventoryItems = inventoryDataAccessObject
                    .getInventory(addToInventoryInputData.getOwnerId())
                    .getItems();

            newInventoryItems.put(addToInventoryInputData.getFoodName(), foodItem);

            final Inventory inventory = inventoryDataAccessObject.getInventory(addToInventoryInputData.getOwnerId());

            inventory.setItems(newInventoryItems);

            inventoryDataAccessObject.updateInventory(inventory);

            isSuccess = inventoryDataAccessObject.getInventory(addToInventoryInputData.getOwnerId())
                    .getItems().containsKey(addToInventoryInputData.getFoodName())
                    && inventoryDataAccessObject.getInventory(addToInventoryInputData.getOwnerId())
                    .getItems().get(addToInventoryInputData.getFoodName()).getQuantity() == 1;
        }

        final AddToInventoryOutputData addToInventoryOutputData =
                new AddToInventoryOutputData(isSuccess, addToInventoryInputData.getOwnerId(), foodItem);
        addToInventoryPresenter.prepareSuccessView(addToInventoryOutputData);

    }

}
