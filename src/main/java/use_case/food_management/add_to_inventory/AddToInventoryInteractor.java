package use_case.food_management.add_to_inventory;

import java.util.Map;

import constants.Constants;
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
                .getItems().containsKey(getFoodName(addToInventoryInputData.getStudySessionLength()))) {

            final String foodName = getFoodName(addToInventoryInputData.getStudySessionLength());

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
            foodItem = getRewardFoodItem(addToInventoryInputData.getStudySessionLength());
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

    AbstractFood getRewardFoodItem(int studySessionLength) {
        AbstractFood foodItem = null;

        if (studySessionLength < Constants.MINUTES_20) {
            foodItem = foodFactory.create("Milk");
        }
        else if (studySessionLength < Constants.MINUTES_40) {
            foodItem = foodFactory.create("Cheese");
        }
        else if (studySessionLength < Constants.MINUTES_60) {
            foodItem = foodFactory.create("Tuna");
        }
        else {
            foodItem = foodFactory.create("Treat");
        }
        return foodItem;
    }

    String getFoodName(int studySessionLength) {
        String foodName = null;

        if (studySessionLength < Constants.MINUTES_20) {
            foodName = "Milk";
        }
        else if (studySessionLength < Constants.MINUTES_40) {
            foodName = "Cheese";
        }
        else if (studySessionLength < Constants.MINUTES_60) {
            foodName = "Tuna";
        }
        else {
            foodName = "Treat";
        }
        return foodName;
    }

}
