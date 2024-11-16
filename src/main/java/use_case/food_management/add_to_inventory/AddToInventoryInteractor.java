package use_case.food_management.add_to_inventory;

import java.util.Map;

import entity.AbstractFood;
import entity.FoodItemFactory;

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
                .getItems().containsKey(addToInventoryInputData.getFoodId())) {

            final Integer prevQuantity = addToInventoryDataAccessObject
                    .getInventory(addToInventoryInputData.getOwnerId())
                    .getItems().get(addToInventoryInputData.getFoodId()).getQuantity();
            foodItem = addToInventoryDataAccessObject.getInventory(addToInventoryInputData.getOwnerId())
                    .getItems().get(addToInventoryInputData.getFoodId());

            addToInventoryDataAccessObject.updateQuantity(addToInventoryInputData.getOwnerId(),
                    addToInventoryInputData.getFoodId(), prevQuantity + 1);

            addToInventoryDataAccessObject.save(addToInventoryDataAccessObject
                    .getInventory(addToInventoryInputData.getOwnerId()));

            isSuccess = addToInventoryDataAccessObject.getInventory(addToInventoryInputData.getOwnerId())
                    .getItems().containsKey(addToInventoryInputData.getFoodId()) && addToInventoryDataAccessObject
                    .getInventory(addToInventoryInputData.getOwnerId())
                    .getItems().get(addToInventoryInputData.getFoodId()).getQuantity() == prevQuantity + 1;

        } // item not in inventory
        else {
            foodItem = foodFactory.create(addToInventoryInputData.getFoodId(), "temp");
            foodItem.setQuantity(1);

            final Map<String, AbstractFood> newInventoryItems = addToInventoryDataAccessObject
                    .getInventory(addToInventoryInputData.getOwnerId())
                    .getItems();
            newInventoryItems.put(addToInventoryInputData.getFoodId(), foodItem);

            addToInventoryDataAccessObject.getInventory(addToInventoryInputData.getOwnerId())
                    .setItems(newInventoryItems);

            isSuccess = addToInventoryDataAccessObject.getInventory(addToInventoryInputData.getOwnerId())
                    .getItems().containsKey(addToInventoryInputData.getFoodId())
                    && addToInventoryDataAccessObject.getInventory(addToInventoryInputData.getOwnerId())
                    .getItems().get(addToInventoryInputData.getFoodId()).getQuantity() == 1;
        }

        final AddToInventoryOutputData addToInventoryOutputData =
                new AddToInventoryOutputData(isSuccess, addToInventoryInputData.getOwnerId(), foodItem);
        addToInventoryPresenter.prepareSuccessView(addToInventoryOutputData);

    }

}
