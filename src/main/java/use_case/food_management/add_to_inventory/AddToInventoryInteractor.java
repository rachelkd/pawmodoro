package use_case.food_management.add_to_inventory;

import java.util.Map;

import entity.AbstractFood;
import entity.FoodItemFactory;
import entity.Inventory;

/**
 * The Add To Inventory Interactor.
 */
public class AddToInventoryInteractor implements AddToInventoryInputBoundary {
    private final AddToInventoryDataAccessInterface addToInventoryDataAccessObject;
    private final AddToInventoryOutputBoundary addToInventoryPresenter;
    private final FoodItemFactory foodFactory;

    public AddToInventoryInteractor(AddToInventoryDataAccessInterface addToInventoryDataAccessObject,
                                    AddToInventoryOutputBoundary addToInventoryOutputBoundary,
                                    FoodItemFactory foodFactory) {
        // repository of inventories
        this.addToInventoryDataAccessObject = addToInventoryDataAccessObject;
        this.addToInventoryPresenter = addToInventoryOutputBoundary;
        this.foodFactory = foodFactory;
    }

    @Override
    public void execute(AddToInventoryInputData addToInventoryInputData) {
        // assume existing inventory
        final boolean isSuccess;
        final AbstractFood foodItem;

        // item in inventory already
        if (addToInventoryDataAccessObject.getInventory(addToInventoryInputData.getOwnerId())
                .getItems().containsKey(addToInventoryInputData.getFoodName())) {

            final int prevQuantity = addToInventoryDataAccessObject
                    .getInventory(addToInventoryInputData.getOwnerId())
                    .getItems().get(addToInventoryInputData.getFoodName()).getQuantity();
            Inventory inventory = addToInventoryDataAccessObject.getInventory(addToInventoryInputData.getOwnerId());
            foodItem = inventory.getItems().get(addToInventoryInputData.getFoodName());
            foodItem.setQuantity(prevQuantity + 1);

            //addToInventoryDataAccessObject.updateInventory(inventory);

            isSuccess = addToInventoryDataAccessObject.getInventory(addToInventoryInputData.getOwnerId())
                    .getItems().containsKey(addToInventoryInputData.getFoodName()) && addToInventoryDataAccessObject
                    .getInventory(addToInventoryInputData.getOwnerId())
                    .getItems().get(addToInventoryInputData.getFoodName()).getQuantity() == prevQuantity + 1;

        } // item not in inventory
        else {
            foodItem = foodFactory.create(addToInventoryInputData.getFoodName());
            foodItem.setQuantity(1);

            final Map<String, AbstractFood> newInventoryItems = addToInventoryDataAccessObject
                    .getInventory(addToInventoryInputData.getOwnerId())
                    .getItems();

            newInventoryItems.put(addToInventoryInputData.getFoodName(), foodItem);

            Inventory inventory = addToInventoryDataAccessObject.getInventory(addToInventoryInputData.getOwnerId());

            // mutable so will pass
            inventory.setItems(newInventoryItems);

            addToInventoryDataAccessObject.updateInventory(inventory);

            isSuccess = addToInventoryDataAccessObject.getInventory(addToInventoryInputData.getOwnerId())
                    .getItems().containsKey(addToInventoryInputData.getFoodName())
                    && addToInventoryDataAccessObject.getInventory(addToInventoryInputData.getOwnerId())
                    .getItems().get(addToInventoryInputData.getFoodName()).getQuantity() == 1;
        }

        final AddToInventoryOutputData addToInventoryOutputData =
                new AddToInventoryOutputData(isSuccess, addToInventoryInputData.getOwnerId(), foodItem);
        addToInventoryPresenter.prepareSuccessView(addToInventoryOutputData);

    }

}
